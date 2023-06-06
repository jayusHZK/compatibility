package com.jayus.smallSpring.step13.context.support;

import java.util.EventObject;

public abstract class ApplicationEvent extends EventObject {

    public ApplicationEvent(Object scurce){
        super(scurce);
    }

}
