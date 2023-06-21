package com.jayus.smallSpring.step14.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * @author : h zk
 * @date : 2023/6/21 14:26
 * @description :
 **/
@Target({ElementType.ANNOTATION_TYPE,ElementType.METHOD,ElementType.FIELD,ElementType.PARAMETER,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Qualifier {

    String value() default "";

}
