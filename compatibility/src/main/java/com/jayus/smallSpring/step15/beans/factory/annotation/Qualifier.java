package com.jayus.smallSpring.step15.beans.factory.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author : h zk
 * @date : 2023/7/6 10:52
 * @description :
 **/
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER,ElementType.TYPE,ElementType.ANNOTATION_TYPE})
public @interface Qualifier {

    String value() default "";

}
