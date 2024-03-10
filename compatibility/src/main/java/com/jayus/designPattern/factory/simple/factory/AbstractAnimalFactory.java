package com.jayus.designPattern.factory.simple.factory;

import com.jayus.designPattern.factory.simple.objects.Animal;

/**
 * 抽象动物工厂
 * 定义生成方式 具体怎么生成由子类实现
 */
public abstract class AbstractAnimalFactory {

    private String color;

    public void setColor(String color) {
        this.color = color;
    }

    public abstract <T extends Animal> T createAnimal(Class<T> c);

}
