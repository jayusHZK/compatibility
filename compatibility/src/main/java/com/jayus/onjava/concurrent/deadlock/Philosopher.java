package com.jayus.onjava.concurrent.deadlock;

/**
 * @author : h zk
 * @date : 2022/8/16 10:37
 * @description :
 **/
/*
要修正死锁问题，你必须明白，当以下四个条件同时满足时，就会发生死锁：

互斥条件。任务使用的资源中至少有一个不能共享的。 这里，一根筷子一次就只能被一个哲学家使用。
至少有一个任务它必须持有一个资源且正在等待获取一个被当前别的任务持有的资源。也就是说，要发生死锁，哲学家必须拿着一根筷子并且等待另一根。
资源不能被任务抢占， 任务必须把资源释放当作普通事件。哲学家很有礼貌，他们不会从其它哲学家那里抢筷子。
必须有循环等待， 这时，一个任务等待其它任务所持有的资源， 后者又在等待另一个任务所持有的资源， 这样一直下去，知道有一个任务在等待第一个任务所持有的资源， 使得大家都被锁住。 在 DiningPhilosophers.java 中， 因为每个哲学家都试图先得到右边的 筷子, 然后得到左边的 筷子, 所以发生了循环等待。
 */
public class Philosopher implements Runnable{

    private final int seat;
    private final StickHolder left,right;

    public Philosopher(int seat, StickHolder left, StickHolder right) {
        this.seat = seat;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "P" + seat;
    }

    @Override
    public void run() {
        while (true) {
            right.pickUp();
            left.pickUp();
            System.out.println(this+ "eating");
            right.putDown();
            left.putDown();
        }
    }
}
