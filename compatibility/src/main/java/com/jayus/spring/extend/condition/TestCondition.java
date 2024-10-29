package com.jayus.spring.extend.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

/**
 * @ClassName TestCondition
 * @Description:
 * @date: 2024/10/28 01:48
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TestCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        for (String s : context.getEnvironment().getActiveProfiles()) {
            System.out.println(s+": "+context.getEnvironment().getProperty("spring.profiles.active"));
        }
        return context.getEnvironment().getProperty("spring.profiles.active").equals("dev");
    }

}
