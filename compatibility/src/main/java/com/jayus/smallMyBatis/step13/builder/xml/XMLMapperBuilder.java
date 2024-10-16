package com.jayus.smallMyBatis.step13.builder.xml;

import com.jayus.smallMyBatis.step13.builder.BaseBuilder;
import com.jayus.smallMyBatis.step13.builder.MapperBuilderAssistant;
import com.jayus.smallMyBatis.step13.io.Resources;
import com.jayus.smallMyBatis.step13.session.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * @ClassName XMLMapperBuilder
 * @Description: XML映射构建器
 * @date: 2024/10/16 11:22
 */
public class XMLMapperBuilder extends BaseBuilder {

    private Element element;

    private MapperBuilderAssistant builderAssistant;

    private String resource;

    public XMLMapperBuilder(InputStream inputStream, Configuration configuration, String resource) throws DocumentException {
        this(new SAXReader().read(inputStream),configuration,resource);
    }

    public XMLMapperBuilder(Document document, Configuration configuration, String resource) {
        super(configuration);
        this.builderAssistant = new MapperBuilderAssistant(configuration,resource);
        this.element = document.getRootElement();
        this.resource = resource;
    }

    public void parse() throws Exception {
        if (!configuration.isResourceLoaded(resource)) {
            configurationElement(element);
            configuration.addLoadedResource(resource);
            configuration.addMapper(Resources.classForName(builderAssistant.getCurrentNamespace()));
        }
    }

    private void configurationElement (Element element) {
        String namespace = element.attributeValue("namaspace");
        if (namespace.equals("")) {
            throw new RuntimeException("Mapper's namespace cannot be empty");
        }
        builderAssistant.setCurrentNameSpace(namespace);

        buildStatementFromContext(element.elements("select"),
                element.elements("insert"),
                element.elements("update"),
                element.elements("delete"));
    }

    private final void buildStatementFromContext(List<Element>... lists){
        for (List<Element> list : lists) {
            for (Element element : list) {
                XMLStatementBuilder statementParser = new XMLStatementBuilder(configuration, builderAssistant, element);
                statementParser.parseStatementNode();
            }
        }
    }

}
