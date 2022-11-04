package com.jayus.onjava.serialization;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * @author : h zk
 * @date : 2022/8/17 19:22
 * @description :
 **/
public class Blip2 implements Externalizable {
    Blip2() {
        System.out.println("Blip2 Constructor");
    }
    @Override
    public void writeExternal(ObjectOutput out)
            throws IOException {
        System.out.println("Blip2.writeExternal");
    }
    @Override
    public void readExternal(ObjectInput in)
            throws IOException, ClassNotFoundException {
        System.out.println("Blip2.readExternal");
    }
}
