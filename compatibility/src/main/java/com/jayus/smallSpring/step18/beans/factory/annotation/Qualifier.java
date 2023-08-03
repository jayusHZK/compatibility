package com.jayus.smallSpring.step18.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * @author : h zk
 * @date : 2023/8/3 18:06
 * @description :
 **/
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER,ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Qualifier {

    String value() default "";

}
