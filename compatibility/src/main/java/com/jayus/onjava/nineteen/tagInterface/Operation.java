package com.jayus.onjava.nineteen.tagInterface;

import java.util.function.Supplier;

/**
 * @author : h zk
 * @date : 2022/8/8 10:22
 * @description :
 **/
public class Operation {

    public final Supplier<String> description;

    public final Runnable command;

    public Operation(Supplier<String> description, Runnable command) {
        this.description = description;
        this.command = command;
    }
}
