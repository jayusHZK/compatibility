package com.jayus.monitor.transformer;

import com.alibaba.fastjson.JSON;
import com.jayus.monitor.service.MyService;
import com.jayus.monitor.util.MethodDesciption;
import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * @ClassName MonitorTransformer
 * @Description:
 * @date: 2024/10/29 19:33
 */
public class MonitorTransformer implements ClassFileTransformer {

    // 配置哪些包下的类可以被家长
    private String config;

    private ClassPool pool;

    public MonitorTransformer(String config, ClassPool pool) {
        this.config = config;
        this.pool = pool;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (className == null || !className.replaceAll("/",".").startsWith(this.config)) {
            return null;
        }
        try {
            className = className.replaceAll("/",".");
            CtClass ctClass = pool.get(className);
            for (CtMethod method : ctClass.getDeclaredMethods()) {
                newMethod(method,pool);
            }
            return ctClass.toBytecode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static final int MAX_NUM = 1024 * 32;

    private final static AtomicInteger index = new AtomicInteger(0);

    private final static Map<Integer, Integer> methodInfos = new ConcurrentHashMap<>();

    // 在分布式环境下使用 ConcurrentHashMap 简单起见，此处使用 AtomicReferenceArray
    private final static AtomicReferenceArray<MethodDesciption> methodTagArray =
            new AtomicReferenceArray<>(MAX_NUM);

    public final static int generateMethodId(Integer hashcode, String clazzName, String methodName
            , List<String> parameterNameList, List<String> parameterTypeList, String returnType) {
        if (methodInfos.containsKey(hashcode)) {
            return methodInfos.get(hashcode);
        }
        MethodDesciption methodDesciption = new MethodDesciption();
        methodDesciption.setClassName(clazzName);
        methodDesciption.setMethodName(methodName);
        methodDesciption.setParameterNameList(parameterNameList);
        methodDesciption.setParameterTypeList(parameterTypeList);
        methodDesciption.setReturnType(returnType);

        int methodId = index.getAndIncrement();
        if (methodId > MAX_NUM) {
            return -1;
        }
        methodTagArray.set(methodId, methodDesciption);
        methodInfos.put(hashcode, methodId);
        return methodId;
    }

    public static void point(final int methodId, final long startNanos, Object[] parameterValues, Object returnValues) {
        MethodDesciption method = methodTagArray.get(methodId);
        System.out.println("方法监控 - BEGIN");
        System.out.println("方法名称： " + method.getClassName() + "." + method.getMethodName());
        System.out.println("方法入参" + JSON.toJSONString(method.getParameterNameList()) + "\n"
                + "入参类型: " + JSON.toJSONString(method.getParameterTypeList()) + "\n"
                + "入参值" + JSON.toJSONString(parameterValues));
        System.out.println("方法出参： " + method.getReturnType() + "\n"
                + "出参值：" + JSON.toJSONString(returnValues));
        System.out.println("方法耗时：" + (System.nanoTime() - startNanos) / 1000_000 + "(s)");
        System.out.println("方法监控 - END \r\n");
    }

    public static void point(final int methodId, Throwable throwable) {
        MethodDesciption method = methodTagArray.get(methodId);
        System.out.println("方法监控 - BEGIN");
        System.out.println("方法名称：" + method.getClassName() + "." + method.getMethodName());
        System.out.println("方法异常：" + throwable.getMessage());
        System.out.println("方法监控 - END\r\n");
    }

    private static void newMethod(CtMethod method, ClassPool pool) throws NotFoundException, CannotCompileException {
        // 获取方法入参类型
        List<String> parameterTypeList = new ArrayList<>();
        for (CtClass parameterType : method.getParameterTypes()) {
            parameterTypeList.add(parameterType.getName());
        }

        List<String> parameterNameList = new ArrayList<>();
        CodeAttribute codeAttribute = method.getMethodInfo().getCodeAttribute();
        LocalVariableAttribute attribute = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        for (int i = 0; i < parameterTypeList.size(); i++) {
            parameterNameList.add(attribute.variableName(i));
        }
        int idx = generateMethodId(method.hashCode(), method.getClass().getName(), method.getName(), parameterNameList, parameterTypeList
                , method.getReturnType().getName());

        method.addLocalVariable("startNanos", CtClass.longType);
        method.insertBefore("startNanos = System.nanoTime();");
        method.addLocalVariable("parameterValues", pool.get(Object[].class.getName()));
        method.insertBefore("parameterValues = $args;");
        method.insertAfter("com.jayus.monitor.transformer.MonitorTransformer.point(" + idx + ",startNanos,parameterValues,$_);", false);
        method.addCatch("com.jayus.monitor.transformer.MonitorTransformer.point("+idx+",$e); throw $e;",pool.get("java.lang.Exception"));
    }

    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.get("com.jayus.monitor.service.MyService");
        CtMethod a = ctClass.getDeclaredMethod("a");
        newMethod(a,pool);
        Class<?> aClass = ctClass.toClass();
        MyService service = (MyService) aClass.getDeclaredConstructor().newInstance();
        service.a("hello");
    }
}
