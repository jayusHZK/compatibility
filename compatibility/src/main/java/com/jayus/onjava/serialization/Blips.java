package com.jayus.onjava.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author : h zk
 * @date : 2022/8/17 19:22
 * @description :
 **/
/*
而对于一个 Externalizable 对象，所有普通的默认构造器都会被调用(包括在字段定义时的初始化）
 */
public class Blips {
    public static void main(String[] args) {
        System.out.println("Constructing objects:");
        Blip1 b1 = new Blip1();
        Blip2 b2 = new Blip2();
        try (ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("Blips.serialized"))) {
            System.out.println("Saving objects:");
            o.writeObject(b1);
            o.writeObject(b2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("Blips.serialized"))) {
            b1 = (Blip1) in.readObject();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
