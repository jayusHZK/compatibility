package com.jayus.smallMyBatis.step11.build.xml;

import com.jayus.smallMyBatis.step11.build.BaseBuilder;
import com.jayus.smallMyBatis.step11.build.MapperBuilderAssistant;
import com.jayus.smallMyBatis.step11.io.Resources;
import com.jayus.smallMyBatis.step11.session.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * @ClassName XMLMapperBuilder
 * @Description: XML 映射构建器
 * @date: 2024/9/18 21:42
 */
public class XMLMapperBuilder extends BaseBuilder {

    private Element element;

    private MapperBuilderAssistant builderAssistant;

    private String resource;

    public XMLMapperBuilder(InputStream inputStream,Configuration configuration,String resource) throws DocumentException {
        this(new SAXReader().read(inputStream),configuration,resource);
    }

    public XMLMapperBuilder(Document document, Configuration configuration, String resource) {
        super(configuration);
        this.element = document.getRootElement();
        this.builderAssistant = new MapperBuilderAssistant(configuration,resource);
        this.resource = resource;
    }

    public void parse() throws Exception {
        // 如果当前资源没有加载过再加载，防止重复加载
        if (!configuration.isResourceLoaded(resource)) {
            configurationElement(element);
            // 标记一下，已经加载过了
            configuration.addLoadedResource(resource);
            // 绑定映射器到namespace Mybatis 源码方法名 -> bindMapperForNamespace
            configuration.addMapper(Resources.classForName(builderAssistant.getCurrentNamespace()));
        }
    }

    private void configurationElement (Element element) {
        // 1.配置namespace
        String namespace = element.attributeValue("namespace");
        if (namespace.equals("")) {
            throw new RuntimeException("Mapper's namespace cannot be empty");
        }
        builderAssistant.setCurrentNamespace(namespace);
        // 2.配置select|insert|update|delete
        buildStatementFromContext(element.elements("select"));
    }

    private void buildStatementFromContext(List<Element> list) {
        for (Element element : list) {
            final XMLStatementBuilder statementParser =
                    new XMLStatementBuilder(configuration, builderAssistant, element);
            statementParser.parseStatementNode();
        }
    }

}
