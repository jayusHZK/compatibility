package com.jayus.jvm.classHeader;

import org.openjdk.jol.info.ClassLayout;

/**
 * 查看java对象实例大小
 */
public class look {

    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

    }

}
