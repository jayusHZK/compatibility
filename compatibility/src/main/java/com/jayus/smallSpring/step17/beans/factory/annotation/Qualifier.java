package com.jayus.smallSpring.step17.beans.factory.annotation;

import org.dom4j.io.SAXReader;

import java.lang.annotation.*;

/**
 * @author : h zk
 * @date : 2023/7/27 11:25
 * @description :
 **/
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER,ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Qualifier {

    String value() default "";

}
