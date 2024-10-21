package com.jayus.jvm.oom;

import com.jayus.smallMyBatis.step11.test.dao.IUserDao;
import com.jayus.smallMyBatis.step11.test.po.User;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ClassName OomTest
 * @Description: 实现各种oom
 * @date: 2024/9/19 01:43
 * -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=256m 设置元空间大小
 * -XX:MaxDirectMemorySize=1g   设置堆外内存限制
 * * -Xms200m -Xmx200m -XX:MaxDirectMemorySize=200m 设置堆内存限制
 * -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/heapdump.hprof  oom 生成堆文件
 */
public class OomTest {

    static List<ByteBuffer> list = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        addShutdownHook();
        cglibProxyTest();
    }

    // 堆溢出
    // java.lang.OutOfMemoryError: Java heap space
    public static void oom() {
        while (1 == 1) {
            // ByteBuffer.allocateDirect 创建堆外内存空间
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);
            //ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 1024);
            list.add(byteBuffer);
        }
    }

    // 堆外内存溢出
    // java.lang.OutOfMemoryError: Direct buffer memory
    public static void oomDirect() {
        while (1 == 1) {
            // ByteBuffer.allocateDirect 创建堆外内存空间
            //ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 1024);
            list.add(byteBuffer);
        }
    }

    // 元空间溢出 只有 cglib 可以 jdk 不行
    // java.lang.OutOfMemoryError: Metaspace
    public static void cglibProxyTest() {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(User.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                    return proxy.invoke(obj, args);
                }
            });
            Object o = enhancer.create();
            System.out.println(o.getClass().getSimpleName());
        }
    }

    public static void jdkProxyTest() {
        while (true) {
            Object o = Proxy.newProxyInstance(IUserDao.class.getClassLoader(), new Class[]{IUserDao.class},
                    ((ob, m, a) -> {
                        System.out.println(1);
                        return 1;
                    }));
            System.out.println(o.getClass().getSimpleName());
        }
    }

    public static void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread((() -> {
            System.out.println("jvm over");
        })));
    }

}
