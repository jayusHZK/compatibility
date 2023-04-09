package com.jayus.smallSpring.step12.aop.framework.adapter;

import com.jayus.smallSpring.step12.aop.MethodBeforeAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/4/9 17:36
 * @Version: 1.0
 */
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
        return invocation;
    }
}