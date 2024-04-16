package com.jayus.smallMyBatis.step10.builder.xml;

import com.jayus.smallMyBatis.step10.builder.BaseBuilder;
import com.jayus.smallMyBatis.step10.mapping.MappedStatement;
import com.jayus.smallMyBatis.step10.mapping.SqlCommandType;
import com.jayus.smallMyBatis.step10.mapping.SqlSource;
import com.jayus.smallMyBatis.step10.scripting.LanguageDriver;
import com.jayus.smallMyBatis.step10.session.Configuration;
import org.dom4j.Element;

import java.util.Locale;

/**
 * XML 语句构建起
 */
public class XMLStatementBuilder extends BaseBuilder {

    private String currentNamespace;

    private Element element;

    public XMLStatementBuilder(Configuration configuration, Element element,String currentNamespace) {
        super(configuration);
        this.element = element;
        this.currentNamespace = currentNamespace;
    }

    public void parseStatementNode(){
        String id = element.attributeValue("id");

        // 参数类型
        String patameterType = element.attributeValue("patameterType");
        Class<?> parameterTypeClass = resolveAlias(patameterType);
        // 结果类型
        String resultType = element.attributeValue("resultType");
        Class<?> resultTypeClass = resolveAlias(resultType);
        //   获取命令类型
        String nodeName = element.getName();
        SqlCommandType sqlCommandType = SqlCommandType.valueOf(nodeName.toUpperCase(Locale.ENGLISH));
        // 获取默认语言驱动器
        Class<?> langClass = configuration.getLanguageRegistry().getDefaultDriverClass();
        LanguageDriver langDriver = configuration.getLanguageRegistry().getDriver(langClass);

        SqlSource sqlSource = langDriver.createSqlSource(configuration, element, parameterTypeClass);

        MappedStatement mappedStatement = new MappedStatement.Builder(configuration,
                currentNamespace + "." + id, sqlCommandType, sqlSource, resultTypeClass).build();

        // 添加解析sql
        configuration.addMapperStatement(mappedStatement);
    }

}
