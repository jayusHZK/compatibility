package com.jayus.asm;



import org.objectweb.asm.*;

import java.util.Arrays;



import static org.objectweb.asm.Opcodes.ASM7;

/**
 * @ClassName test
 * @Description: https://www.xiaogenban1993.com/blog/24.07/java%E7%9A%84asm%E5%BA%93%E6%B7%B1%E5%85%A5%E6%B5%85%E5%87%BA
 一些查看class信息的方法
 * @date: 2024/10/24 15:14
 */
public class classWatch {

    /*
    asm 有两个主要的库 asm-core和asm-tree，tree基于core，更加结构化和面向对象。
    asm整体设计基于 visitor 模式，就是扫描(visit)字节码的过程中，不同的阶段和不同的操作
    都会调用一些hook函数，这些函数默认都是空操作，我们可以利用这些hook，执行一些逻辑，最终
    完成我们想要的效果。
    ClassReader 是读取字节码的类，使用这个类读取了字节码才能进行后续操作，他可以接收类名
    也可以接收 byte[] 作为参数。
     */

    public static void main(String[] args) throws Exception {
        //ClassVisitorMethodTest();
        //FieldVisitorTest();
        MethodVisitorTest();
    }

    // 可直接查看类的 asm 生成代码
    public static void test(){
        //org.objectweb.asm.util.ASMifier.main(new String[]{"com.jayus.asm.source.MyRunnable"});
    }

    /*
    ClassVisitor
     */
    public static void ClassVisitorMethodTest() throws Exception {
        ClassReader cr = new ClassReader("com.jayus.asm.source.MyRunnable");
        cr.accept(new ClassVisitor(ASM7) {
            // 开始读取
            @Override
            public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
                System.out.printf("Now visit class start, classFileVersion: %s, access:%s, className: %s, signature:%s, superClass:%s, interfaces:%s%n",
                        version, access, name, signature, superName, Arrays.toString(interfaces));
            }

            // 读取字段
            @Override
            public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
                System.out.printf("Now visit field, access:%s, name:%s, descriptor:%s, signature:%s, value:%s%n",
                        access, name, descriptor, signature, value);
                return null;
            }

            // 读取方法
            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                System.out.printf("Now visit method, access:%s, name:%s, descriptor:%s, signature:%s, exceptions:%s%n",
                        access, name, descriptor, signature, Arrays.toString(exceptions));
                return null;
            }

            //读取结束
            @Override
            public void visitEnd() {
                System.out.println("Now visit class finished");
            }
        },0);
    }

    /*
    FieldVisitor
     */
    public static void FieldVisitorTest() throws Exception{
        ClassReader cr = new ClassReader("com.jayus.asm.source.MyRunnable");
        cr.accept(new ClassVisitor(ASM7) {
            @Override
            public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
                super.visit(version, access, name, signature, superName, interfaces);
            }

            @Override
            public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
                System.out.printf("Now visit field, access:%s, name:%s, descriptor:%s, signature:%s, value:%s%n",
                        access, name, descriptor, signature, value);
                return new FieldVisitor(ASM7) {
                    @Override
                    public void visitAttribute(Attribute attribute) {
                        System.out.println("- Attribute: "+attribute);
                    }

                    @Override
                    public void visitEnd() {
                        System.out.printf("- Now visit field %s finished%n", name);
                    }
                };
            }

            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                return super.visitMethod(access, name, descriptor, signature, exceptions);
            }

            @Override
            public void visitEnd() {
                super.visitEnd();
            }
        },0);
    }


    public static void MethodVisitorTest() throws Exception{
        ClassReader cr = new ClassReader("com.jayus.asm.source.MyRunnable");
        cr.accept(new ClassVisitor(ASM7) {
            @Override
            public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
                super.visit(version, access, name, signature, superName, interfaces);
            }

            @Override
            public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
                return super.visitField(access,name,descriptor,signature,value);
            }

            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                System.out.printf("Now visit method, access:%s, name:%s, descriptor:%s, signature:%s, exceptions:%s%n",
                        access, name, descriptor, signature, Arrays.toString(exceptions));
                return new MethodVisitor(ASM7) {
                    @Override
                    public void visitParameter(String pname, int access) {
                        System.out.printf("- Method:%s Parameter:%s%n", name, pname);
                    }

                    @Override
                    public void visitCode() {
                        System.out.printf("- Method:%s code start%n", name);
                    }

                    @Override
                    public void visitMethodInsn(int opcode, String owner, String mname, String descriptor, boolean isInterface) {
                        System.out.printf("- Method:%s, invoke other method %s%s%n", name, owner, mname);
                    }

                    @Override
                    public void visitLineNumber(int line, Label start) {
                        System.out.printf("- Method:%s, current line number %s%n", name, line);
                    }

                    @Override
                    public void visitMaxs(int maxStack, int maxLocals) {
                        System.out.printf("- Method:%s, maxStack:%s, maxLocals:%s%n", name, maxStack, maxLocals);
                    }

                    @Override
                    public void visitEnd() {
                        System.out.printf("- Method:%s, visit finished%n", name);
                    }
                };
            }

            @Override
            public void visitEnd() {
                super.visitEnd();
            }
        },0);
    }
}
