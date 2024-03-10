package com.jayus.jvm.classLoaderTest;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * @ClassName ClassLoaderOOM
 * @Description: 制造元空间溢出
 * @date: 2024/3/4 21:11
 */
public class ClassLoaderOOM extends ClassLoader{

    // 添加 jvm 参数 -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
    public static void main(String[] args) {
        ClassLoaderOOM clo = new ClassLoaderOOM();
        int i = 0;
        while (true){
            ClassWriter cw = new ClassWriter(0);
            String className = "Class" + i++;
            // 指明版本号、修饰符、类名、包名、父类、接口
            cw.visit(Opcodes.V1_8,Opcodes.ACC_PUBLIC,className,null,"java/lang/Object",null);
            byte[] code = cw.toByteArray();
            clo.defineClass(className,code,0,code.length);
        }
    }

}
