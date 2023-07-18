package com.jayus.smallSpring.step16.aop.aspectj;

import com.jayus.smallSpring.step16.aop.Pointcut;
import com.jayus.smallSpring.step16.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * @author : h zk
 * @date : 2023/7/18 9:56
 * @description :
 **/
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    private AspectJExpressionPointcut pointcut;

    private Advice advice;

    private String expression;

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public Pointcut getPointcut() {
        if (null == pointcut){
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
