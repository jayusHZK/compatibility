package com.jayus.smallSpring.step15.stereotype;

import java.lang.annotation.*;

/**
 * @author : h zk
 * @date : 2023/7/10 16:44
 * @description :
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {

    String value() default "";

}
