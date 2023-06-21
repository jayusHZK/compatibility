package com.jayus.smallSpring.step14.beans.factory.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author : h zk
 * @date : 2023/6/21 14:27
 * @description :
 **/
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER})
public @interface Value {

    String value();

}
