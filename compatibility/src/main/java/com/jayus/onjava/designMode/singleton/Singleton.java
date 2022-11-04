package com.jayus.onjava.designMode.singleton;

/**
 * @author : h zk
 * @date : 2022/8/16 16:00
 * @description :
 **/
public class Singleton implements Cloneable{
    private static final class ResourceImpl implements Resource{

        private int i;

        private ResourceImpl(int i) {
            this.i = i;
        }

        @Override
        public synchronized int getValue() {
            return i;
        }

        @Override
        public synchronized void setValue(int x) {
            i = x;
        }
    }

    private static class ResourceHolder{
        private static Resource resource = new ResourceImpl(47);
    }

    public static Resource getResource(){
        return ResourceHolder.resource;
    }

    public static void main(String[] args) {
        Resource r = Singleton.getResource();
        System.out.println(r.getValue());
        Resource r2 = Singleton.getResource();
        r2.setValue(9);
        System.out.println(r.getValue());
        //r2.clone();
    }
}
