package com.jayus.smallMyBatis.step19.builder.xml;

import com.jayus.smallMyBatis.step19.builder.BaseBuilder;
import com.jayus.smallMyBatis.step19.builder.MapperBuilderAssistant;
import com.jayus.smallMyBatis.step19.executor.keygen.Jdbc3KeyGenerator;
import com.jayus.smallMyBatis.step19.executor.keygen.KeyGenerator;
import com.jayus.smallMyBatis.step19.executor.keygen.NoKeyGenerator;
import com.jayus.smallMyBatis.step19.executor.keygen.SelectKeyGenerator;
import com.jayus.smallMyBatis.step19.mapping.MappedStatement;
import com.jayus.smallMyBatis.step19.mapping.SqlCommandType;
import com.jayus.smallMyBatis.step19.mapping.SqlSource;
import com.jayus.smallMyBatis.step19.scripting.LanguageDriver;
import com.jayus.smallMyBatis.step19.session.Configuration;
import org.dom4j.Element;

import java.util.List;
import java.util.Locale;

/**
 * @ClassName XMLStatementBuilder
 * @Description: XML 语句构建器
 * @date: 2024/10/20 20:52
 */
public class XMLStatementBuilder extends BaseBuilder {

    private MapperBuilderAssistant builderAssistant;

    private Element element;

    public XMLStatementBuilder(Configuration configuration, MapperBuilderAssistant builderAssistant, Element element) {
        super(configuration);
        this.builderAssistant = builderAssistant;
        this.element = element;
    }

    public void parseStatementNode() {
        String id = element.attributeValue("id");
        String parameterType = element.attributeValue("parameterType");
        Class<?> parameterTypeClass = resolveAlias(parameterType);
        // 外部应用map
        String resultMap = element.attributeValue("resultMap");
        // 结果类型
        String resultType = element.attributeValue("resultType");
        Class<?> resultTypeClass = resolveAlias(resultType);
        String nodeName = element.getName();
        SqlCommandType sqlCommandType = SqlCommandType.valueOf(nodeName.toUpperCase(Locale.ENGLISH));

        boolean isSelect = sqlCommandType == SqlCommandType.SELECT;
        boolean flushCache = Boolean.parseBoolean(element.attributeValue("flushCache", String.valueOf(!isSelect)));
        boolean useCache = Boolean.parseBoolean(element.attributeValue("useCache", String.valueOf(!isSelect)));

        Class<?> langClass = configuration.getLanguageRegistry().getDefaultDriverClass();
        LanguageDriver langDriver = configuration.getLanguageRegistry().getDriver(langClass);

        processSelectKeyNodes(id, parameterTypeClass, langDriver);

        SqlSource sqlSource = langDriver.createSqlSource(configuration, element, parameterTypeClass);

        String keyProperty = element.attributeValue("keyProperty");

        KeyGenerator keyGenerator = null;

        String keyStatementId = id + SelectKeyGenerator.SELECT_KEY_SUFFIX;
        keyStatementId = builderAssistant.applyCurrentNamespace(keyStatementId, true);

        if (configuration.hasKeyGenerator(keyStatementId)) {
            keyGenerator = configuration.getKeyGenerator(keyStatementId);
        } else {
            keyGenerator = configuration.isUseGeneratedKeys() && SqlCommandType.INSERT.equals(SqlCommandType.INSERT)
                    ? new Jdbc3KeyGenerator() : new NoKeyGenerator();
        }

        builderAssistant.addMappedStatement(id, sqlSource, sqlCommandType, parameterTypeClass, resultMap
                , resultTypeClass, flushCache, useCache, keyGenerator, keyProperty, langDriver);
    }

    private void processSelectKeyNodes(String id, Class<?> parameterTypeClass, LanguageDriver langDriver) {
        List<Element> selectNodes = element.elements("selectKey");
        parseSelectKeyNodes(id, selectNodes, parameterTypeClass, langDriver);
    }

    private void parseSelectKeyNodes(String parentId, List<Element> list, Class<?> parameterTypeClass, LanguageDriver langDriver) {
        for (Element nodeToHandle : list) {
            String id = parentId + SelectKeyGenerator.SELECT_KEY_SUFFIX;
            parseSelectKeyNode(id, nodeToHandle, parameterTypeClass, langDriver);
        }
    }

    private void parseSelectKeyNode(String id, Element nodeToHandle, Class<?> parameterTypeClass, LanguageDriver langDriver) {
        String resultType = nodeToHandle.attributeValue("resultType");
        Class<?> resultTypeClass = resolveClass(resultType);
        boolean executeBefore = "BEFORE".equals(nodeToHandle.attributeValue("order", "AFTER"));
        String keyProperty = nodeToHandle.attributeValue("keyProperty");

        String resultMap = null;
        boolean flushCache = false;
        boolean useCache = false;
        KeyGenerator keyGenerator = new NoKeyGenerator();

        SqlSource sqlSource = langDriver.createSqlSource(configuration, nodeToHandle, parameterTypeClass);
        SqlCommandType sqlCommandType = SqlCommandType.SELECT;

        builderAssistant.addMappedStatement(id, sqlSource, sqlCommandType, parameterTypeClass, resultMap, resultTypeClass, flushCache
                , useCache, keyGenerator, keyProperty, langDriver);

        MappedStatement keyStatement = configuration.getMappedStatement(id);
        configuration.addKeyGenerator(id, new SelectKeyGenerator(keyStatement, executeBefore));
    }

}
