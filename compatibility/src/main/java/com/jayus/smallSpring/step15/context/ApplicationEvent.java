package com.jayus.smallSpring.step15.context;

import java.util.EventObject;

/**
 * @author : h zk
 * @date : 2023/7/10 16:31
 * @description :
 **/
public abstract class ApplicationEvent extends EventObject {

    public ApplicationEvent(Object source) {
        super(source);
    }
}
