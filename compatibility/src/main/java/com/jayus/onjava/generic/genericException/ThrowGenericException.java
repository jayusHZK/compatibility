package com.jayus.onjava.generic.genericException;

/**
 * @author : h zk
 * @date : 2022/8/11 11:38
 * @description :
 **/
public class ThrowGenericException {
    public static void main(String[] args) {
        ProcessRunner<String, Failure1> runner = new ProcessRunner<>();
        for (int i = 0; i < 3; i++) {
            runner.add(new Processor1());
        }
        try {
            System.out.println(runner.processAll());
        } catch (Failure1 failure1) {
            failure1.printStackTrace();
        }
        ProcessRunner<Integer, Failure2> runner2 = new ProcessRunner<>();
        for (int i = 0; i < 3; i++) {
            runner2.add(new Processor2());
        }
        try {
            System.out.println(runner2.processAll());
        } catch (Failure2 failure2) {
            failure2.printStackTrace();
        }
    }
}
