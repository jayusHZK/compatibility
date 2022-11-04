package com.jayus.onjava.serialization;

import java.io.*;

/**
 * @author : h zk
 * @date : 2022/8/17 19:29
 * @description :
 **/
public class Blip3 implements Externalizable {
    private int i;

    private String s;

    public Blip3() {
        System.out.println("Blip3 Constructor");
    }

    public Blip3(int i, String s) {
        System.out.println("Blip3(String x, int a)");
        this.i = i;
        this.s = s;
    }

    @Override
    public String toString() {
        return "Blip3{" +
                "i=" + i +
                ", s='" + s + '\'' +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip3.writeExternal");
        // 关键代码
        out.writeObject(s);
        out.writeInt(i);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip3.readExternal");
        // 关键代码 去掉拿不到数据
        s = (String) in.readObject();
        i = in.readInt();
    }

    public static void main(String[] args) {
        System.out.println("Constructing objects:");
        Blip3 b3 = new Blip3(47,"A String ");
        System.out.println(b3);
        try(ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("Blip3.serialized"))){
            System.out.println("Saving object:");
            o.writeObject(b3);
        }catch (Exception e){
            e.printStackTrace();
        }
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Blip3.serialized"))){
            b3 = (Blip3) ois.readObject();
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(b3);
    }
}
