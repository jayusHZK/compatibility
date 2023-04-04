package com.jayus.smallSpring.step11.aop.framework;

import org.aopalliance.intercept.MethodInvocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * @author : h zk
 * @date : 2023/4/4 15:21
 * @description :
 **/
public class ReflectiveMethodInvocation implements MethodInvocation {

    // 目标对象
    protected final Object target;

    // 方法
    protected final Method method;

    // 入参
    protected final Object[] arguments;

    public ReflectiveMethodInvocation(Object target, Method method, Object[] arguments) {
        this.target = target;
        this.method = method;
        this.arguments = arguments;
    }

    @Nonnull
    @Override
    public Method getMethod() {
        return method;
    }

    @Nonnull
    @Override
    public Object[] getArguments() {
        return arguments;
    }

    @Nullable
    @Override
    public Object proceed() throws Throwable {
        return method.invoke(target,arguments);
    }

    @Nullable
    @Override
    public Object getThis() {
        return target;
    }

    @Nonnull
    @Override
    public AccessibleObject getStaticPart() {
        return method;
    }
}
