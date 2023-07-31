package com.jayus.smallSpring.step17.context;

import java.util.EventObject;

/**
 * @author : h zk
 * @date : 2023/7/31 16:02
 * @description :
 **/
public abstract class ApplicationEvent extends EventObject {

    public ApplicationEvent(Object source) {
        super(source);
    }
}
