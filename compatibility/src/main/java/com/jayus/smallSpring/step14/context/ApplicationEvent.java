package com.jayus.smallSpring.step14.context;

import java.util.EventObject;

/**
 * @author : h zk
 * @date : 2023/6/26 16:57
 * @description :
 **/
public abstract class ApplicationEvent extends EventObject {

    public ApplicationEvent(Object source) {
        super(source);
    }
}
