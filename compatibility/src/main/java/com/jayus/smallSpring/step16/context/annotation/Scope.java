package com.jayus.smallSpring.step16.context.annotation;

import java.lang.annotation.*;

/**
 * @author : h zk
 * @date : 2023/7/17 14:28
 * @description :
 **/
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scope {

    String value() default "singleton";

}
