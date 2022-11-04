package com.jayus.onjava.generic.genericException;

import java.util.List;

/**
 * @author : h zk
 * @date : 2022/8/11 10:13
 * @description :
 **/
public interface Processor<T, E extends Exception> {
    void process(List<T> resultCollector) throws E;
}
