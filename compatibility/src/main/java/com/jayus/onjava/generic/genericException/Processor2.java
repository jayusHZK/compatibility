package com.jayus.onjava.generic.genericException;

import java.util.List;

/**
 * @author : h zk
 * @date : 2022/8/11 11:30
 * @description :
 **/
public class Processor2 implements Processor<Integer,Failure2>{

    static int count = 2;

    @Override
    public void process(List<Integer> resultCollector) throws Failure2 {
        if (count -- == 0)
            resultCollector.add(47);
        else
            resultCollector.add(11);
        if (count < 0)
            throw new Failure2();

    }
}
