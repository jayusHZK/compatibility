package com.jayus.test.event;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : h zk
 * @date : 2023/4/4 9:57
 * @description :
 **/
public class MsgManager {

    static List<MsgListener> msgListenerList = new ArrayList<>();

    public static void addListener(MsgListener msgListener) {
        msgListenerList.add(msgListener);
    }

    public static void sendMsg(MsgEvent event) throws Exception {
        notifyListener(event);
    }

    public static void notifyListener(MsgEvent event) throws Exception {
        for (MsgListener listener : msgListenerList) {
            listener.handleMsg(event);
        }
    }

    public static void main(String[] args) {
        try {
            MsgManager.addListener(new MsgListener() {
                @Override
                public void handleMsg(MsgEvent msgEvent) throws Exception {
                    System.out.println("MsgListenerA," + msgEvent.getStatus());
                }
            });
            MsgManager.addListener(new MsgListener() {
                @Override
                public void handleMsg(MsgEvent msgEvent) throws Exception {
                    System.out.println("MsgListenerB," + msgEvent.getStatus());
                }
            });
            MsgEvent test = new MsgEvent("test", 11);
            sendMsg(test);
            MsgEvent test2 = new MsgEvent("test",999);
            sendMsg(test2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
