package com.jayus.smallMyBatis.step19.builder.xml;

import com.jayus.smallMyBatis.step19.builder.BaseBuilder;
import com.jayus.smallMyBatis.step19.builder.MapperBuilderAssistant;
import com.jayus.smallMyBatis.step19.builder.ResultMapResolver;
import com.jayus.smallMyBatis.step19.cache.Cache;
import com.jayus.smallMyBatis.step19.io.Resources;
import com.jayus.smallMyBatis.step19.mapping.ResultFlag;
import com.jayus.smallMyBatis.step19.mapping.ResultMap;
import com.jayus.smallMyBatis.step19.mapping.ResultMapping;
import com.jayus.smallMyBatis.step19.session.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.*;

/**
 * @ClassName XMLMapperBuilder
 * @Description: XML 映射构建器
 * @date: 2024/10/20 20:29
 */
public class XMLMapperBuilder extends BaseBuilder {

    private Element element;

    private MapperBuilderAssistant builderAssistant;

    private String resource;

    public XMLMapperBuilder(InputStream inputStream, Configuration configuration, String resource) throws DocumentException {
        this(new SAXReader().read(inputStream), configuration, resource);
    }

    private XMLMapperBuilder(Document document, Configuration configuration, String resource) {
        super(configuration);
        this.builderAssistant = new MapperBuilderAssistant(configuration, resource);
        this.element = document.getRootElement();
        this.resource = resource;
    }

    public void parse() throws Exception {
        if (!configuration.isResourceLoader(resource)) {
            configurationElement(element);
            configuration.addLoadedResource(resource);
            configuration.addMapper(Resources.classForName(builderAssistant.getCurrentNamespace()));
        }
    }

    private void configurationElement(Element element) {
        String namespace = element.attributeValue("namespace");
        if (namespace.equals("")) {
            throw new RuntimeException("Mapper's namespace cannot be empty");
        }
        builderAssistant.setCurrentNamespace(namespace);
        cacheElement(element.element("cache"));

        resultMapElements(element.elements("resultMap"));

        buildStatementFromContext(element.elements("select")
                , element.elements("insety")
                , element.elements("update")
                , element.elements("delete"));
    }

    private void cacheElement(Element context) {
        if (context == null) return;
        // 基础配置信息
        String type = context.attributeValue("type", "PERPETUAL");
        Class<? extends Cache> typeClass = typeAliasRegistry.resolveAlias(type);
        // 缓存队列 FIFO
        String eviction = context.attributeValue("eviction", "FIFO");
        Class<? extends Cache> evictionClass = typeAliasRegistry.resolveAlias(eviction);
        Long flushInterval = Long.valueOf(context.attributeValue("flushInterval"));
        Integer size = Integer.valueOf(context.attributeValue("size"));
        boolean readWrite = !Boolean.parseBoolean(context.attributeValue("readOnly", "false"));
        boolean blocking = !Boolean.parseBoolean(context.attributeValue("blocking", "false"));

        List<Element> elements = context.elements();
        Properties props = new Properties();
        for (Element element : elements) {
            props.setProperty(element.attributeValue("name"), context.attributeValue("value"));
        }
        builderAssistant.useNewCache(typeClass, evictionClass, flushInterval, size, readWrite, blocking, props);
    }

    private void resultMapElements(List<Element> list) {
        for (Element element : list) {
            try {
                resultMapElement(element, Collections.emptyList());
            } catch (Exception e) {

            }
        }
    }

    private ResultMap resultMapElement(Element resultMapNode, List<ResultMapping> additionalResultMappings) {
        String id = resultMapNode.attributeValue("id");
        String type = resultMapNode.attributeValue("type");
        Class<?> typeClass = resolveClass(type);

        List<ResultMapping> resultMappings = new ArrayList<>();
        resultMappings.addAll(additionalResultMappings);

        List<Element> resultChildren = resultMapNode.elements();
        for (Element resultChild : resultChildren) {
            List<ResultFlag> flags = new ArrayList<>();
            if ("id".equals(resultChild.getName())) {
                flags.add(ResultFlag.ID);
            }
            resultMappings.add(buildResultMappingFromContext(resultChild, typeClass, flags));
        }
        ResultMapResolver resultMapResolver = new ResultMapResolver(builderAssistant, id, typeClass, resultMappings);
        return resultMapResolver.resolve();
    }

    private ResultMapping buildResultMappingFromContext(Element context, Class<?> resultType, List<ResultFlag> flags) {
        String property = context.attributeValue("property");
        String column = context.attributeValue("column");
        return builderAssistant.buildResultMapping(resultType, property, column, flags);
    }

    private final void buildStatementFromContext(List<Element>... lists) {
        for (List<Element> list : lists) {
            for (Element element : list) {
                final XMLStatementBuilder statementParser = new XMLStatementBuilder(configuration, builderAssistant, element);
                statementParser.parseStatementNode();
            }
        }
    }


}
