package com.jayus.smallSpring.step17.context.annotation;

import java.lang.annotation.*;

/**
 * @author : h zk
 * @date : 2023/8/1 10:08
 * @description :
 **/
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scope {

    String value() default "singleton";

}
