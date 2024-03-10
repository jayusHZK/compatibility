package com.jayus.designPattern.responsibility.handler;

import com.jayus.designPattern.responsibility.RequestBody;

public class HusbandHandler extends Handler{

    public HusbandHandler() {
        super(HUSBAND);
    }

    @Override
    public void response(RequestBody req) {
        System.out.println(this.getClass().getSimpleName()+":yes");
    }
}
