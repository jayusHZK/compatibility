package com.jayus.smallSpring.step14.context.annotation;

import java.lang.annotation.*;

/**
 * @author : h zk
 * @date : 2023/6/26 17:02
 * @description :
 **/
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scope {

    String value() default "singleton";

}
