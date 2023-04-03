package com.jayus.smallSpring.step10.event;

import com.jayus.smallSpring.step10.context.event.ApplicationContextEvent;

/**
 * @author : h zk
 * @date : 2023/4/3 17:56
 * @description :
 **/
public class CustomEvent extends ApplicationContextEvent {

    private Long id;
    private String message;

    public CustomEvent(Object source, Long id, String message) {
        super(source);
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
