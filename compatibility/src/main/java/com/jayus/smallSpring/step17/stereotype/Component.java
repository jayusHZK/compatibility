package com.jayus.smallSpring.step17.stereotype;

import java.lang.annotation.*;

/**
 * @author : h zk
 * @date : 2023/8/1 10:12
 * @description :
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {

    String value() default "";

}
