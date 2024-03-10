package com.jayus.designPattern.decoration;

public class Decoration extends Base {

    @Override
    public void inDoor() {
        System.out.println("买点礼物");
        super.inDoor();
    }

    @Override
    public void outDoor() {
        System.out.println("喝杯茶");
        super.outDoor();
    }

    public static void main(String[] args) {
        Decoration decoration = new Decoration();
        decoration.inDoor();
        decoration.outDoor();
    }
}
