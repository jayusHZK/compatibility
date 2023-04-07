package com.jayus.smallSpring.step11.test;

import com.jayus.smallSpring.step11.aop.AdvisedSupport;
import com.jayus.smallSpring.step11.aop.MethodMatcher;
import com.jayus.smallSpring.step11.aop.TargetSource;
import com.jayus.smallSpring.step11.aop.aspectj.AspectJExpressionPointcut;
import com.jayus.smallSpring.step11.aop.framework.Cglib2AopProxy;
import com.jayus.smallSpring.step11.aop.framework.JdkDynamicApoProxy;
import com.jayus.smallSpring.step11.aop.framework.ReflectiveMethodInvocation;
import com.jayus.smallSpring.step11.bean.IUserService;
import com.jayus.smallSpring.step11.bean.UserService;
import com.jayus.smallSpring.step11.bean.UserServiceInterceptor;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author : h zk
 * @date : 2023/4/7 11:05
 * @description :
 **/
public class Test {

    public static void main(String[] args) throws Exception {
        //test_aop();
        //test_dynamic();
        //test_proxy_class();
        test_proxy_method();
    }

    public static void test_aop() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* com.jayus.smallSpring.step11.bean.UserService.*(..))");
        Class<UserService> clazz = UserService.class;
        Method method = clazz.getDeclaredMethod("queryUserInfo");

        System.out.println(pointcut.matches(clazz));
        System.out.println(pointcut.matches(method, clazz));
    }

    public static void test_dynamic() {
        UserService userService = new UserService();
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.jayus.smallSpring.step11.bean.UserService.*(..))"));

        IUserService proxy_jdk = (IUserService) new JdkDynamicApoProxy(advisedSupport).getProxy();
        System.out.println("测试结果：" + proxy_jdk.queryUserInfo());
        IUserService proxy_cglib = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();
        System.out.println("测试结果：" + proxy_cglib.register("mingou"));
    }

    public static void test_proxy_class() {
        IUserService userService = (IUserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IUserService.class}, (proxy, method, args) -> "你被代理了！");
        System.out.println(userService.queryUserInfo());
    }

    public static void test_proxy_method() {
        Object targetObj = new UserService();
        IUserService proxy = (IUserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), targetObj.getClass().getInterfaces(), new InvocationHandler() {

            MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* com.jayus.smallSpring.step11.bean.IUserService.*(..))");

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (methodMatcher.matches(method, targetObj.getClass())) {
                    MethodInterceptor methodInterceptor = invocation -> {
                        long start = System.currentTimeMillis();
                        try {
                            return invocation.proceed();
                        } finally {
                            System.out.println("监控 - Begin By AOP");
                            System.out.println("方法名称：" + invocation.getMethod().getName());
                            System.out.println("方法耗时：" + (System.currentTimeMillis() - start) + "ms");
                            System.out.println("监控 - End\r\n");
                        }
                    };
                    return methodInterceptor.invoke(new ReflectiveMethodInvocation(targetObj,method,args));
                }
                return method.invoke(targetObj,args);
            }
        });
        System.out.println(proxy.queryUserInfo());
    }

}
