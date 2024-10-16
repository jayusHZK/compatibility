package com.jayus.smallMyBatis.step12.script.xmltags;

import com.jayus.smallMyBatis.step12.executor.parameter.ParameterHandler;
import com.jayus.smallMyBatis.step12.mapping.BoundSql;
import com.jayus.smallMyBatis.step12.mapping.MappedStatement;
import com.jayus.smallMyBatis.step12.mapping.SqlSource;
import com.jayus.smallMyBatis.step12.script.LanguageDriver;
import com.jayus.smallMyBatis.step12.script.defaults.DefaultParameterHandler;
import com.jayus.smallMyBatis.step12.session.Configuration;
import org.dom4j.Element;

/**
 * @ClassName XMLLanguageDriver
 * @Description: XML 语言驱动器
 * @date: 2024/9/26 13:42
 */
public class XMLLanguageDriver implements LanguageDriver {

    @Override
    public SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType) {
        XMLScriptBuilder builder = new XMLScriptBuilder(configuration, script, parameterType);
        return builder.parseScriptNode();
    }

    @Override
    public ParameterHandler createParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
        return new DefaultParameterHandler(mappedStatement,parameterObject,boundSql);
    }
}
