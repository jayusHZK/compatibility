package com.jayus.onjava.serialization;

import java.io.*;
import java.util.Random;

/**
 * @author : h zk
 * @date : 2022/8/17 18:21
 * @description :
 **/
public class Worm implements Serializable {
    private static Random rand = new Random(47);
    private Data[] d = {
            new Data(rand.nextInt(10)),new Data(rand.nextInt(10)),
            new Data(rand.nextInt(10))
    };
    private Worm next;
    private char c;

    public Worm(int i,char x) {
        System.out.println("Worm constructor: " + i);
        this.c = x;
        if (--i > 0) {
            next = new Worm(i, (char) (x+1));
        }
    }

    public Worm() {
        System.out.println("No-arg constructor");
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(":");
        result.append(c);
        result.append("(");
        for (Data dat : d) {
            result.append(dat);
        }
        result.append(")");
        if (next != null) {
            result.append(next);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Worm w = new Worm(6, 'a');
        System.out.println("w= " + w);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("worm.dat"))){
            out.writeObject("Worm stotage\n");
            out.writeObject(w);
        }catch (Exception e) {
            e.printStackTrace();
        }
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("worm.dat"))){
            String s = (String) in.readObject();
            Worm w2 = (Worm) in.readObject();
            System.out.println(s + "w2" + w2);
        }catch (Exception e) {
            e.printStackTrace();
        }
        try(ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream out2 = new ObjectOutputStream(bout)){
            out2.writeObject("Worm storage\n");
            out2.writeObject(w);
            out2.flush();
            try(ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bout.toByteArray()))){
                String s = (String) ois.readObject();
                Worm w3 = (Worm) ois.readObject();
                System.out.println(s + "w3" + w3 + " ");
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
