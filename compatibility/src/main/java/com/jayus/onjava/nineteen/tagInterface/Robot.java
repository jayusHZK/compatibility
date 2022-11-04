package com.jayus.onjava.nineteen.tagInterface;

import java.util.List;

/**
 * @author : h zk
 * @date : 2022/8/8 10:20
 * @description :
 **/
public interface Robot {
    String name();

    String model();

    List<Operation> operations();

    static void test(Robot r) {
        if (r instanceof NullInterface)
            System.out.println("[Null Robot]");
        System.out.println("Robot name:" + r.name());
        System.out.println("Robot model:" + r.model());
        for (Operation operation : r.operations()) {
            System.out.println(operation.description.get());
            operation.command.run();
        }
    }
}
