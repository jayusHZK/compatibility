package com.jayus.designPattern.factory.simple.objects;

public abstract class AbstractAnimal implements Animal{

    private String color;

    public void setColor(String color) {
        this.color = color;
    }

    public void sayName(){
        System.out.println(getName()+color);
    }

}
