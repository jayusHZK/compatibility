package com.jayus.smallSpring.step18.stereotype;

import java.lang.annotation.*;

/**
 * @author : h zk
 * @date : 2023/8/2 16:38
 * @description :
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {

    String value() default "";

}
