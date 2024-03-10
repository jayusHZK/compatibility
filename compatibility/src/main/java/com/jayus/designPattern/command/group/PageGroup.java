package com.jayus.designPattern.command.group;

public class PageGroup implements Group{

    @Override
    public void add() {
        System.out.println(this.getClass().getSimpleName()+":add");
    }

    @Override
    public void del() {
        System.out.println(this.getClass().getSimpleName()+":del");
    }

    @Override
    public void update() {
        System.out.println(this.getClass().getSimpleName()+":update");
    }

    @Override
    public void get() {
        System.out.println(this.getClass().getSimpleName()+":get");
    }
}
