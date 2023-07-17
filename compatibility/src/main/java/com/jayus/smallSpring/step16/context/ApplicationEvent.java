package com.jayus.smallSpring.step16.context;

import java.util.EventObject;

/**
 * @author : h zk
 * @date : 2023/7/17 15:13
 * @description :
 **/
public abstract class ApplicationEvent extends EventObject {

    public ApplicationEvent(Object source) {
        super(source);
    }
}
