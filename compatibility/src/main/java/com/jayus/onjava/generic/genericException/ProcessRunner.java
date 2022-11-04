package com.jayus.onjava.generic.genericException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : h zk
 * @date : 2022/8/11 10:17
 * @description :
 **/
public class ProcessRunner<T,E extends Exception> extends ArrayList<Processor<T,E>> {
    List<T> processAll() throws E {
        List<T> resultCollector = new ArrayList<>();
        for (Processor<T, E> processor : this) {
            processor.process(resultCollector);
        }
        return resultCollector;
    }
}
