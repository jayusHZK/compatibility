package com.jayus.designPattern.prototype;

/**
 * @ClassName test
 * @Description: 原型模式
 * @date: 2024/10/27 18:51
 */
public class test {

    public static void main(String[] args) {
        Manager manager = new Manager();
        MessageBox mbox = new MessageBox('*');
        manager.register("mbox",mbox);
        Product p1 = manager.create("mbox");
        p1.use("hello! ");
    }

}
