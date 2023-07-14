package com.jayus.smallSpring.step16.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * @author : h zk
 * @date : 2023/7/14 11:32
 * @description :
 **/
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER,ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Qualifier {

    String value() default "";

}
