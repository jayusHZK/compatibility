package com.jayus.smallSpring.step03;

import com.jayus.smallSpring.bean.UserService2;
import com.jayus.smallSpring.step03.factory.config.BeanDefinition;
import com.jayus.smallSpring.step03.factory.support.DefaultListableBeanFactory;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/10 23:43
 * @Version: 1.0
 */
public class Test {

    public static void main(String[] args) {
        beanTest1();
        test_cglib();
        test_newInstance();
        test_constructor();
        test_paramterTypes();
    }

    public static void beanTest1() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(UserService2.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);
        UserService2 userService2 = (UserService2) beanFactory.getBean("userService", "mingou");
        userService2.queryUserInfo();
    }

    public static void test_cglib() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService2.class);
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        Object obj = enhancer.create(new Class[]{String.class}, new Object[]{"mingou"});
        System.out.println(obj);
    }

    public static void test_newInstance() {
        try {
            UserService2 userService2 = UserService2.class.newInstance();
            System.out.println(userService2);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void test_constructor(){
        try {
            Class<UserService2> userService2Class = UserService2.class;
            Constructor<UserService2> declaredConstructor = userService2Class.getDeclaredConstructor(String.class);
            UserService2 userService2 = declaredConstructor.newInstance("mingou");
            System.out.println(userService2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test_paramterTypes(){
        Class<UserService2> userService2Class = UserService2.class;
        Constructor<?>[] declaredConstructors = userService2Class.getDeclaredConstructors();
        Constructor constructor = null;
        for (Constructor<?> item : declaredConstructors) {
            if (item.getParameterTypes().length == 1){
                constructor = item;
                break;
            }
        }
        try {
            Constructor<UserService2> declaredConstructor = userService2Class.getDeclaredConstructor(constructor.getParameterTypes());
            UserService2 userService2 = declaredConstructor.newInstance("mingou");
            System.out.println(userService2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}