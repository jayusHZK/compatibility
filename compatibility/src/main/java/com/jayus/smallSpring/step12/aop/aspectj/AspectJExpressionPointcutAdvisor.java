package com.jayus.smallSpring.step12.aop.aspectj;

import com.jayus.smallSpring.step12.aop.Pointcut;
import com.jayus.smallSpring.step12.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * @author : h zk
 * @date : 2023/4/7 15:21
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
        if (pointcut == null){
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
