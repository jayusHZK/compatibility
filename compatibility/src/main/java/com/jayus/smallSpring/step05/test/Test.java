package com.jayus.smallSpring.step05.test;

import cn.hutool.core.io.IoUtil;
import com.jayus.smallSpring.step05.bean.UserDao;
import com.jayus.smallSpring.step05.bean.UserService;
import com.jayus.smallSpring.step05.beans.PropertyValue;
import com.jayus.smallSpring.step05.beans.PropertyValues;
import com.jayus.smallSpring.step05.beans.factory.config.BeanDefinition;
import com.jayus.smallSpring.step05.beans.factory.config.BeanReference;
import com.jayus.smallSpring.step05.beans.factory.support.DefaultListableBeanFactory;
import com.jayus.smallSpring.step05.beans.factory.xml.XmlBeanDefinitionReader;
import com.jayus.smallSpring.step05.core.io.DefaultResourceLoader;
import com.jayus.smallSpring.step05.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/13 22:35
 * @Version: 1.0
 */
public class Test {

    private static DefaultResourceLoader resourceLoader = new DefaultResourceLoader();

    public static void main(String[] args) throws IOException {
        //test_BeanFactory();
        //test_classpath();
        //test_file();
        //test_url();
        test_xml();
    }

    public static void test_BeanFactory(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("userDao",new BeanDefinition(UserDao.class));
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uid","1"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));

        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService",beanDefinition);

        UserService userService = (UserService) beanFactory.getBean("userService");
        System.out.println(userService.queryUserInfo());

    }

    public static void test_classpath() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    public static void test_file() throws IOException {
        Resource resource = resourceLoader.getResource("E:\\idea\\ccc\\compatibility\\compatibility\\src\\main\\java\\com\\jayus\\smallSpring\\step05\\important.properties");
        InputStream inputStream = resource.getInputStream();
        System.out.println(IoUtil.readUtf8(inputStream));
    }

    public static void test_url() throws IOException {
        Resource resource = resourceLoader.getResource("https://github.com/fuzhengwei/small-spring/blob/main/important.properties");
        InputStream inputStream = resource.getInputStream();
        System.out.println(IoUtil.readUtf8(inputStream));
    }

    public static void test_xml(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");
        UserService userService = beanFactory.getBean("userService", UserService.class);
        System.out.println(userService.queryUserInfo());
    }

}