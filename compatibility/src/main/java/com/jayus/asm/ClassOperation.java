package com.jayus.asm;

import org.objectweb.asm.*;
import org.objectweb.asm.commons.AdviceAdapter;
import org.objectweb.asm.tree.*;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.Arrays;

import static org.objectweb.asm.Opcodes.*;

/**
 * @ClassName ClsssOperation
 * @Description: 操作class
 * @date: 2024/10/24 15:56
 * ideal安装 asm插件 asm bytecode viewer 可以直接查看类怎么用asm生成，右键类，选择 asm bytecode viewer ,
 * 然后选择 asmlified 栏即可查看，复制出来新建类直接运行dump方法即可生成其字节码
 */
public class ClassOperation {

    public static void main(String[] args) throws Exception {
        ClassReader cr = new ClassReader("com.jayus.asm.source.MyRunnable");
        //deleteMethod(cr);
        //insertMethod(cr);
        //updMethod(cr);
        //updMethodAdapter(cr);
        //updMethodCode(cr);
        updMethodCodeTree(cr);
    }

    // 删除方法
    public static void deleteMethod(ClassReader cr) throws Exception {
        ClassWriter cw = new ClassWriter(0);
        cr.accept(new ClassVisitor(ASM7, cw) {
            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                // 如果当前方法名是 a 则返回空
                if (name.equals("a")) return null;
                return super.visitMethod(access, name, descriptor, signature, exceptions);
            }
        }, 0);
        OutputStream o = new FileOutputStream("delMethod.class");
        o.write(cw.toByteArray());
        o.close();
    }

    // 新增方法，看不懂，全是字节码操作，要看得懂字节码才能使用
    public static void insertMethod(ClassReader cr) throws Exception {
        ClassWriter cw = new ClassWriter(0);
        // 自动计算操作栈数 相当于 mv.visitMaxs(2,1); 可以都填0 性能不如自己算
        //ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        cr.accept(new ClassVisitor(ASM7, cw) {
            @Override
            public void visitEnd() {
                MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "b", "()V", null, null);
                // 下面这段直接对应代码System.out.println("b");
                mv.visitCode();
                mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitLdcInsn("b");
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/Object;)V", false);
                mv.visitInsn(RETURN);
                mv.visitMaxs(2, 1);
                mv.visitEnd();
                super.visitEnd();
            }
        }, 0);
        byte[] codes = cw.toByteArray();
        ClassLoader cl = ClassOperation.class.getClassLoader();
        Method define = ClassLoader.class.getDeclaredMethod("defineClass", byte[].class, int.class, int.class);
        define.setAccessible(true);
        Class<?> c = (Class<?>) define.invoke(cl, codes, 0, codes.length);
        System.out.println(Arrays.toString(c.getDeclaredMethods()));
    }

    // 修改方法
    public static void updMethod(ClassReader cr) throws Exception {
        ClassWriter cw = new ClassWriter(0);
        cr.accept(new ClassVisitor(ASM7, cw) {
            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                MethodVisitor originalMV = super.visitMethod(access, name, descriptor, signature, exceptions);
                if (name.equals("a")) {
                    return new MethodVisitor(ASM7, originalMV) {
                        // 变量序号，暂定100，不能和已有的冲突
                        int startTimeVarIndex = 100;

                        @Override
                        public void visitCode() {
                            super.visitCode();
                            // 开始之后插入一段代码记录局部变量
                            visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
                            visitVarInsn(LSTORE, startTimeVarIndex);
                        }

                        @Override
                        public void visitInsn(int opcode) {
                            if (opcode == IRETURN) {
                                visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                                // return 之前插入一段代码计算耗时
                                visitMethodInsn(INVOKESTATIC, "java/lang/Ststem", "currentTimeMillis", "()J", false);
                                visitVarInsn(LLOAD, startTimeVarIndex);
                                visitInsn(LSUB);
                                visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V", false);
                            }
                            super.visitInsn(opcode);
                        }
                    };
                } else {
                    return originalMV;
                }
            }

            ;
            // 避免一些验证性问题
        }, ClassReader.EXPAND_FRAMES);
        OutputStream o = new FileOutputStream("updMethod.class");
        o.write(cw.toByteArray());
        o.close();
    }

    // 修改方法，借助 Adapter;adapter 不止拦截 return，还会拦截 throw
    public static void updMethodAdapter(ClassReader cr) throws Exception {
        ClassWriter cw = new ClassWriter(0);
        cr.accept(new ClassVisitor(ASM7, cw) {
            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                if (name.equals("a")) {
                    MethodVisitor mv = cw.visitMethod(access, name, descriptor, signature, exceptions);
                    return new AdviceAdapter(ASM7, mv, access, name, descriptor) {
                        private int startTimeVarIndex;

                        // 函数进入的时候，添加一行 long startTime = System.currentTimeMillis();
                        @Override
                        protected void onMethodEnter() {
                            visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
                            startTimeVarIndex = newLocal(Type.LONG_TYPE);
                            storeLocal(startTimeVarIndex); // 等价于 mv.visitVarInsn(LSTORE, startTimeVarIndex);
                        }

                        // 函数退出的时候，添加一行 System.out.println(System.currentTimeMillis() - startTime);
                        @Override
                        protected void onMethodExit(int opcode) {
                            visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                            visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
                            loadLocal(startTimeVarIndex);
                            visitInsn(LSUB);
                            visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V", false);
                        }
                    };
                } else {
                    return super.visitMethod(access, name, descriptor, signature, exceptions);
                }
            }

            ;
            // 避免一些验证性问题
        }, ClassReader.EXPAND_FRAMES);
        OutputStream o = new FileOutputStream("updMethodAdapter.class");
        o.write(cw.toByteArray());
        o.close();
    }

    // 修改代码
    public static void updMethodCode(ClassReader cr) throws Exception {
        ClassWriter cw = new ClassWriter(0);
        cr.accept(new ClassVisitor(ASM7, cw) {
            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                if (name.equals("a")) {
                    MethodVisitor mv = cw.visitMethod(access, name, descriptor, signature, exceptions);
                    return new AdviceAdapter(ASM7, mv, access, name, descriptor) {
                        private int dIndex = -1;

                        // 针对 a 方法中调用的 Math.random方法的hook
                        @Override
                        public void visitMethodInsn(int opcodeAndSource, String owner, String name, String descriptor, boolean isInterface) {
                            if (owner.equals("java/lang/Math") && name.equals("random")) {
                                // 如果变量 D 没有赋值默认给了个 -1
                                if (dIndex < 0) {
                                    super.visitMethodInsn(opcodeAndSource, owner, name, descriptor, isInterface);
                                    dup2();
                                    dIndex = newLocal(Type.DOUBLE_TYPE);
                                    storeLocal(dIndex);
                                } else {
                                    // 如果已经赋值，直接加载变量，而不是运行random函数
                                    loadLocal(dIndex);
                                }
                                return;
                            }
                            // 非random方法还是按照原来的不作改动
                            super.visitMethodInsn(opcodeAndSource, owner, name, descriptor, isInterface);
                        }
                    };
                }
                return super.visitMethod(access, name, descriptor, signature, exceptions);
            }
        }, ClassReader.EXPAND_FRAMES);
        OutputStream o = new FileOutputStream("updMethodCode.class");
        o.write(cw.toByteArray());
        o.close();
    }

    // 使用 tree 包修改方法信息，直接改指令
    public static void updMethodCodeTree(ClassReader cr) throws Exception {
        ClassNode classNode = new ClassNode();
        cr.accept(classNode, ClassReader.EXPAND_FRAMES);
        MethodNode methodNode = classNode.methods.stream().filter(it -> it.name.equals("a")).findAny().get();
        InsnList newInsnList = new InsnList();
        boolean first = true;
        for (AbstractInsnNode instruction : methodNode.instructions) {
            // 解析每条指令
            if (instruction instanceof MethodInsnNode) {
                MethodInsnNode methodInsnNode = (MethodInsnNode) instruction;
                if (methodInsnNode.name.equals("random") && methodInsnNode.owner.equals("java/lang/Math")) {
                    if (first) {
                        //第一次运行就添加到局部变量
                        first = false;
                        newInsnList.add(instruction);
                        newInsnList.add(new InsnNode(DUP2));
                        // methodNode.maxLocals是当前局部变量数量，那么下一个的下标就是他
                        newInsnList.add(new VarInsnNode(DSTORE, methodNode.maxLocals));
                    } else {
                        // 之后运行的话，就读取局部变量的值
                        newInsnList.add(new VarInsnNode(DLOAD, methodNode.maxLocals));
                    }
                    continue;
                }
            }
            newInsnList.add(instruction);
        }
        methodNode.instructions.clear();
        methodNode.instructions.add(newInsnList);
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        classNode.accept(cw);
        OutputStream o = new FileOutputStream("updMethodCodeTree.class");
        o.write(cw.toByteArray());
        o.close();
    }

}
