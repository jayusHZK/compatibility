package com.jayus.smallSpring.step19.core;

import com.jayus.smallSpring.step19.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BridgeMethodResolver {

    public BridgeMethodResolver() {
    }

    public static Method findBridgeMethod(Method bridgeMethod) {
        if (!bridgeMethod.isBridge()) { // 是不是桥接方法
            return bridgeMethod;
        }
        List<Method> candidateMethods = new ArrayList<>();
        Method[] methods = ReflectionUtils.getAllDeclareMethods(bridgeMethod.getDeclaringClass()); // 获取所有方法
        for (Method candidateMethod : methods) {
            if (isBridgeCandidateFor(candidateMethod,bridgeMethod)){
                candidateMethods.add(candidateMethod);
            }
        }
        if (candidateMethods.size() == 1){
            return candidateMethods.get(0);
        }
        //sea
        return null;
    }

    private static boolean isBridgeCandidateFor(Method candidateMethod,Method bridgeMethod){
        return !candidateMethod.isBridge() && !candidateMethod.equals(bridgeMethod) &&
                candidateMethod.getName().equals(bridgeMethod.getName()) &&
                candidateMethod.getParameterCount() == bridgeMethod.getParameterCount();
    }

    private static Method searchCandidates(List<Method> candidateMethods,Method bridgeMethod){
        if (candidateMethods.isEmpty()){
            return null;
        }
        Method previousMethod = null;
        boolean sameSig = true;
        for (Method candidateMethod : candidateMethods) {
            //if (is)
        }
        return null;
    }

    static boolean isBridgeMethodFor(Method bridgeMethod,Method candidateMethod,Class<?> declaringClass){
        //if (is)
        return false;
    }

    private static boolean isResolvedTypeMatch(Method genericMethod,Method candidateMethod,Class<?> declaringClass){
        Type[] genericParameterTypes = genericMethod.getGenericParameterTypes();
        Class<?>[] candidateParameters = candidateMethod.getParameterTypes();
        if (genericParameterTypes.length!=candidateParameters.length){
            return false;
        }
        for (int i = 0; i < candidateParameters.length; i++) {

        }
        return false;
    }

}
