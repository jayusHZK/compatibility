package com.jayus.onjava.generic.genericException;

import java.util.List;

/**
 * @author : h zk
 * @date : 2022/8/11 11:21
 * @description :
 **/
public class Processor1 implements Processor<String,Failure1>{

    static int count = 3;

    @Override
    public void process(List<String> resultCollector) throws Failure1 {
        if (count --> 1)
            resultCollector.add("Hep!");
        else
            resultCollector.add("Ho!");
        if (count < 0)
            throw new Failure1();
    }


}
