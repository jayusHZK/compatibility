package com.jayus.test.event;

import java.util.EventListener;

/**
 * @author : h zk
 * @date : 2023/4/4 9:56
 * @description :
 **/
public interface MsgListener extends EventListener {

    void handleMsg(MsgEvent msgEvent) throws Exception;

}
