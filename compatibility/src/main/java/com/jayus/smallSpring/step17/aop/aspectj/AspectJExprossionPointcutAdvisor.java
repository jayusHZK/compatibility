package com.jayus.smallSpring.step17.aop.aspectj;

import com.jayus.smallSpring.step17.aop.Pointcut;
import com.jayus.smallSpring.step17.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * @author : h zk
 * @date : 2023/8/1 16:30
 * @description :
 **/
public class AspectJExprossionPointcutAdvisor implements PointcutAdvisor {

    protected AspectJExprossionPointcut pointcut;

    private Advice advice;

    private String expression;

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public Pointcut getPointcut() {
        if (null == pointcut){
            pointcut = new AspectJExprossionPointcut(expression);
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
