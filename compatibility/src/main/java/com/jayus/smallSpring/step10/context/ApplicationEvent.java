package com.jayus.smallSpring.step10.context;

import java.util.EventObject;

/**
 * @author : h zk
 * @date : 2023/4/3 14:07
 * @description :
 **/
public abstract class ApplicationEvent extends EventObject {


    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
