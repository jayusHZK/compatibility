package com.jayus.smallSpring.step06.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.jayus.smallSpring.step06.beans.BeansException;
import com.jayus.smallSpring.step06.beans.PropertyValue;
import com.jayus.smallSpring.step06.beans.factory.config.BeanDefinition;
import com.jayus.smallSpring.step06.beans.factory.config.BeanReference;
import com.jayus.smallSpring.step06.beans.factory.support.AbstractBeanDefinitionReader;
import com.jayus.smallSpring.step06.beans.factory.support.BeanDefinitionRegistry;
import com.jayus.smallSpring.step06.core.io.Resource;
import com.jayus.smallSpring.step06.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: h zk
 * @Description:
 * @Date: 2023/3/14 23:25
 * @Version: 1.0
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            try(InputStream inputStream = resource.getInputStream()){
                doLoadBeanDefinitions(inputStream);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new BeansException("IOException parsing XML document from " + resource, e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(String... locations) throws BeansException {
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }

    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
        Document doc = XmlUtil.readXML(inputStream);
        Element root = doc.getDocumentElement();
        NodeList childNodes = root.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            if (!(childNodes.item(i) instanceof Element)) continue;
            if (!childNodes.item(i).getNodeName().equals("bean")) continue;
            Element bean = (Element) childNodes.item(i);
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");
            String beanName = StrUtil.isNotBlank(id) ? id: name;
            Class<?> clazz = Class.forName(className);
            if (StrUtil.isEmpty(beanName)){
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                if (!(childNodes.item(j) instanceof Element)) continue;
                if (!childNodes.item(j).getNodeName().equals("property")) continue;
                Element property = (Element) bean.getChildNodes().item(i);
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String attrRef = property.getAttribute("ref");
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
                PropertyValue propertyValue = new PropertyValue(attrName,value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            if (getRegistry().containsBeanDefinition(beanName)){
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
            }
            getRegistry().registerBeanDefinition(beanName,beanDefinition);
        }
    }

}