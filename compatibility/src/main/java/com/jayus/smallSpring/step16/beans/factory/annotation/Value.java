package com.jayus.smallSpring.step16.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * @author : h zk
 * @date : 2023/7/14 11:36
 * @description :
 **/
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Value {

    String value();

}
