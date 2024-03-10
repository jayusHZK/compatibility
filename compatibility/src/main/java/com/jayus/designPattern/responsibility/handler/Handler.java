package com.jayus.designPattern.responsibility.handler;

import com.jayus.designPattern.responsibility.RequestBody;

/**
 * 抽象处理器
 */
public abstract class Handler {

    // 对应等级
    public final static int FATHER = 1;

    public final static int HUSBAND = 2;

    public final static int SON = 3;

    // 默认等级
    private Integer level = 0;

    // 下一个处理器
    private Handler next;

    // 构造器需要指定可以处理的等级
    public Handler(Integer level) {
        this.level = level;
    }

    public final void handlerMessage(RequestBody req){
        if (req.getType() == level){
            this.response(req);
        } else {
            if (req.getType() != null){
                if (this.next != null){
                    this.next.handlerMessage(req);
                } else {
                    System.out.println("no body,refuse");
                }
            }
        }
    }

    public void setNext(Handler next) {
        this.next = next;
    }

    public abstract void response(RequestBody req);
}
