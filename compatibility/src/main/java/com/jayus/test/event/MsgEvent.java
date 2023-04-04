package com.jayus.test.event;

import java.util.EventObject;

/**
 * @author : h zk
 * @date : 2023/4/4 9:55
 * @description :
 **/
public class MsgEvent extends EventObject {

    private int status;

    public MsgEvent(Object source) {
        super(source);
    }

    public MsgEvent(Object source, int status) {
        super(source);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
