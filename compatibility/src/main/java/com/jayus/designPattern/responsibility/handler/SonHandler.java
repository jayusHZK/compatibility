package com.jayus.designPattern.responsibility.handler;

import com.jayus.designPattern.responsibility.RequestBody;

public class SonHandler extends Handler{

    public SonHandler() {
        super(SON);
    }

    @Override
    public void response(RequestBody req) {
        System.out.println(this.getClass().getSimpleName()+":yes");
    }
}
