package com.jayus.smallSpring.step14.aop.aspectj;

import com.jayus.smallSpring.step14.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * @author : h zk
 * @date : 2023/6/27 15:00
 * @description :
 **/
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    // 切面
    private AspectJExpressionPointcut pointcut;

    // 具体的拦截方法
    private Advice advice;

    // 表达式
    private String expression;

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public AspectJExpressionPointcut getPointcut() {
        if (null == pointcut) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }
}
