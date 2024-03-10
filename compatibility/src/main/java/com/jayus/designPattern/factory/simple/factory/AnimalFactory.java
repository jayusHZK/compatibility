package com.jayus.designPattern.factory.simple.factory;

import com.jayus.designPattern.factory.simple.objects.AbstractAnimal;
import com.jayus.designPattern.factory.simple.objects.Animal;
import com.jayus.designPattern.factory.simple.objects.Dog;

/**
 * 动物工厂
 * 具体对象生成方式
 */
public class AnimalFactory extends AbstractAnimalFactory {

    /**
     * 可以在这里对对象的生成进行一些处理 比如 设置对象属性
     *
     * @param c
     * @param <T>
     * @return
     */
    @Override
    public <T extends Animal> T createAnimal(Class<T> c) {
        AbstractAnimal animal = null;
        try {
            animal = (AbstractAnimal) c.newInstance();
            if (c == Dog.class) {
                animal.setColor("黑色");
            } else {
                animal.setColor("白色");
            }
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return (T) animal;
    }
}
