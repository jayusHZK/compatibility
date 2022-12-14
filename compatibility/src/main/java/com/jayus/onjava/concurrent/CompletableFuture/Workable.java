package com.jayus.onjava.concurrent.CompletableFuture;

import com.jayus.onjava.concurrent.createTask.Nap;

import java.util.concurrent.CompletableFuture;

/**
 * @author : h zk
 * @date : 2022/8/15 19:53
 * @description :
 **/
public class Workable {
    String id;
    final double duration;

    public Workable(String id, double duration) {
        this.id = id;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Workable{" +
                "id='" + id + '\'' +
                ", duration=" + duration +
                '}'+System.currentTimeMillis();
    }

    public static Workable work(Workable tt) {
        new Nap(tt.duration);
        tt.id = tt.id + "W";
        System.out.println(tt);
        return tt;
    }

    public static CompletableFuture<Workable> make(String id, double duration) {
        return CompletableFuture.completedFuture(new Workable(id,duration)).thenApplyAsync(Workable::work);
    }
}
