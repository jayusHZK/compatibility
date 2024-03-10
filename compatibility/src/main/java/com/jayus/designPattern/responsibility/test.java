package com.jayus.designPattern.responsibility;

import com.jayus.designPattern.responsibility.handler.FatherHandler;
import com.jayus.designPattern.responsibility.handler.Handler;
import com.jayus.designPattern.responsibility.handler.HusbandHandler;
import com.jayus.designPattern.responsibility.handler.SonHandler;

public class test {

    public static void main(String[] args) {
        Handler father = new FatherHandler();
        Handler husband = new HusbandHandler();
        Handler son = new SonHandler();
        father.setNext(husband);
        husband.setNext(son);
        RequestBody requestBody = new RequestBody(2);
        father.handlerMessage(requestBody);
    }

}
