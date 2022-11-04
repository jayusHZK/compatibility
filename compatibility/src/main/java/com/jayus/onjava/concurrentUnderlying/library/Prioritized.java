package com.jayus.onjava.concurrentUnderlying.library;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : h zk
 * @date : 2022/8/17 14:37
 * @description :
 **/
public class Prioritized implements Comparable<Prioritized> {
    private static AtomicInteger counter = new AtomicInteger();
    private final int id = counter.getAndIncrement();
    private final int priority;
    private static List<Prioritized> sequence = new CopyOnWriteArrayList<>();

    public Prioritized(int priority) {
        this.priority = priority;
        sequence.add(this);
    }

    @Override
    public int compareTo(Prioritized o) {
        return priority < o.priority ? 1 : (priority > o.priority ? -1 : 0);
    }

    @Override
    public String toString() {
        return String.format("[%d] Prioritized %d", priority, id);
    }

    public void displaySequence() {
        int count = 0;
        for (Prioritized pt : sequence) {
            System.out.printf("(%d:%d)",pt.id,pt.priority);
            if (++count %5 == 0) System.out.println();
        }
    }

    public static class EndSentinel extends Prioritized{
        EndSentinel(){super(-1);}
    }
}
