package com.jayus.smallSpring.step18.aop.framework;

import org.aopalliance.intercept.MethodInvocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

public class ReflectiveMethodInvocation implements MethodInvocation {

    protected final Object target;

    protected final Method method;

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
