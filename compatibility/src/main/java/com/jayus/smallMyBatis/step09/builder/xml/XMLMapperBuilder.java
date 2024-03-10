package com.jayus.smallMyBatis.step09.builder.xml;

import com.jayus.smallMyBatis.step09.builder.BaseBuilder;
import com.jayus.smallMyBatis.step09.io.Resources;
import com.jayus.smallMyBatis.step09.session.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * XML 映射构建器
 */
public class XMLMapperBuilder extends BaseBuilder {

    private Element element;

    private String resource;

    private String currentNamespace;

    public XMLMapperBuilder(InputStream inputStream, Configuration configuration, String resource) throws DocumentException {
        this(new SAXReader().read(inputStream), configuration, resource);
    }

    public XMLMapperBuilder(Document document, Configuration configuration, String resource) {
        super(configuration);
        this.element = document.getRootElement();
        this.resource = resource;
    }

    public void parse() throws Exception {
        // 如果当前资源没有加载过再加载 防止重复加载
        if (!configuration.isResourceLoaded(resource)){
            configurationElement(element);
            // 标记一下 已经加载过了
            configuration.addLoadedResource(resource);
            // 绑定映射器到 namespace
            configuration.addMapper(Resources.classForName(currentNamespace));
        }
    }

    // 配置mapper元素
    // <mapper namespace="org.mybatis.example.BlogMapper">
    //   <select id="selectBlog" parameterType="int" resultType="Blog">
    //    select * from Blog where id = #{id}
    //   </select>
    // </mapper>
    private void configurationElement(Element element){
        currentNamespace = element.attributeValue("namespace");
        if (currentNamespace.equals("")){
            throw new RuntimeException("Mapper's namespace cannot be empty");
        }
        buildStatementFromContext(element.elements("select"));
    }

    // 配置select|insert|update|delete
    private void buildStatementFromContext(List<Element> list){
        for (Element element : list) {
            final XMLStatementBuilder statementParse = new XMLStatementBuilder(configuration, element, currentNamespace);
            statementParse.parseStatementNode();
        }
    }

}
