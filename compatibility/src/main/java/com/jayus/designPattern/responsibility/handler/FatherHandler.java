package com.jayus.designPattern.responsibility.handler;

import com.jayus.designPattern.responsibility.RequestBody;

public class FatherHandler extends Handler {

    public FatherHandler() {
        super(FATHER);
    }

    @Override
    public void response(RequestBody req) {
        System.out.println(this.getClass().getSimpleName()+":yes");
    }
}
