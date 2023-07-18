package com.jayus.smallSpring.step16;

import com.jayus.smallSpring.step16.bean.Husband;
import com.jayus.smallSpring.step16.bean.Wife;
import com.jayus.smallSpring.step16.context.support.ClassPathXmlApplicationContext;

/**
 * @author : h zk
 * @date : 2023/7/18 16:25
 * @description :
 **/
public class test {

    public static void main(String[] args) {
        test_circular();
    }

    public static void test_circular(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        Husband husband = applicationContext.getBean("husband", Husband.class);
        Wife wife = applicationContext.getBean("wife", Wife.class);
        System.out.println(husband.queryWife());
        System.out.println(wife.queryHusband());
    }

}
