package com.jayus.smallSpring.step18.aop.framework.adapter;

import com.jayus.smallSpring.step18.aop.MethodBeforeAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MethodBeforeAdviceInterceptor implements MethodInterceptor {

    private MethodBeforeAdvice advice;

    public MethodBeforeAdviceInterceptor() {
    }

    public MethodBeforeAdviceInterceptor(MethodBeforeAdvice advice) {
        this.advice = advice;
    }

    @Nullable
    @Override
    public Object invoke(@Nonnull MethodInvocation invocation) throws Throwable {
        this.advice.before(invocation.getMethod(),invocation.getArguments(),invocation.getThis());
        return invocation.proceed();
    }

    public MethodBeforeAdvice getAdvice() {
        return advice;
    }

    public void setAdvice(MethodBeforeAdvice advice) {
        this.advice = advice;
    }
}
