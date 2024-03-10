package com.jayus.designPattern.factory.simple.factory;

import com.jayus.designPattern.factory.simple.objects.Dog;

public class test {

    public static void main(String[] args) {
        AnimalFactory animalFactory = new AnimalFactory();
        System.out.println(animalFactory.createAnimal(Dog.class).getName());
        animalFactory.createAnimal(Dog.class).sayName();
    }

}
