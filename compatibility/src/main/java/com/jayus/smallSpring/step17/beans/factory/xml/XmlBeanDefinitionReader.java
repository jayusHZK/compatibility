package com.jayus.smallSpring.step17.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import com.jayus.smallSpring.step17.beans.factory.support.AbstractBeanDefinitionReader;
import com.jayus.smallSpring.step17.beans.factory.support.BeanDefinitionRegistry;
import com.jayus.smallSpring.step17.core.io.ResourceLoader;

import java.io.InputStream;

/**
 * @author : h zk
 * @date : 2023/7/31 15:58
 * @description :
 **/
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    protected void doLoadBeanDefinitions(InputStream inputStream) {

    }

    private void scanPackage(String scanPath){
        StrUtil
    }

}
