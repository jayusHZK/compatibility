package com.jayus.smallSpring.step18.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * @author : h zk
 * @date : 2023/8/3 18:07
 * @description :
 **/
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Value {

    String value();

}
