package com.jayus.smallSpring.step14.stereotype;

import java.lang.annotation.*;

/**
 * @author : h zk
 * @date : 2023/6/20 17:52
 * @description :
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {

    String value() default "";

}
