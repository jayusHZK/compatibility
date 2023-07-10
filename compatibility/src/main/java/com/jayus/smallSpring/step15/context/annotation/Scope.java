package com.jayus.smallSpring.step15.context.annotation;

import java.lang.annotation.*;

/**
 * @author : h zk
 * @date : 2023/7/10 16:54
 * @description :
 **/
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scope {

    String value() default "singleton";

}
