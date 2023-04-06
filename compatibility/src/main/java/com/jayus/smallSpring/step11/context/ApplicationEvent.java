package com.jayus.smallSpring.step11.context;

import java.util.EventObject;

/**
 * @author : h zk
 * @date : 2023/4/6 17:10
 * @description :
 **/
public class ApplicationEvent extends EventObject {

    public ApplicationEvent(Object source) {
        super(source);
    }
}
