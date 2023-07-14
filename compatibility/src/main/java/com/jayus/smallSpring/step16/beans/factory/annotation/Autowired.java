package com.jayus.smallSpring.step16.beans.factory.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : h zk
 * @date : 2023/7/14 11:31
 * @description :
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.CONSTRUCTOR,ElementType.FIELD,ElementType.METHOD})
public @interface Autowired {
}
