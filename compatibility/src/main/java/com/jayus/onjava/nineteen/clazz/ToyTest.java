package com.jayus.onjava.nineteen.clazz;

/**
 * @author : h zk
 * @date : 2022/8/1 17:05
 * @description :
 **/
public class ToyTest {
    static void printInfo(Class cc) {
        System.out.println("Class name:" + cc.getName() + "is interface? [" + cc.isInterface() + "]");
        System.out.println("Simple name:" + cc.getSimpleName());
        System.out.println("Canonical name :" + cc.getCanonicalName());
        System.out.println("--------");
    }

    public static void main(String[] args) {
        FancyToy fancyToy = new FancyToy();
        System.out.println(fancyToy instanceof Toy);

        System.out.println(Toy.class.isInstance(fancyToy));

        Class  c = null;

        try {
            c = Class.forName("com.jayus.onjava.nineteen.clazz.FancyToy");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        printInfo(c);
        for (Class item : c.getInterfaces()) {
            printInfo(item);
        }
        Class up = c.getSuperclass();

        Object obj = null;

        try {
            obj = up.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println(up.getSuperclass());
        printInfo(obj.getClass());
    }
}
