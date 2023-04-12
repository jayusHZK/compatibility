package com.jayus.smallSpring.step12.context;

import java.util.EventObject;

/**
 * @author : h zk
 * @date : 2023/4/12 11:23
 * @description :
 **/
public abstract class ApplicationEvent extends EventObject {

    public ApplicationEvent(Object source) {
        super(source);
    }
}
