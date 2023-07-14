package com.jayus.smallSpring.step16.stereotype;

import java.lang.annotation.*;

/**
 * @author : h zk
 * @date : 2023/7/14 10:19
 * @description :
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {

    String value() default "";

}
