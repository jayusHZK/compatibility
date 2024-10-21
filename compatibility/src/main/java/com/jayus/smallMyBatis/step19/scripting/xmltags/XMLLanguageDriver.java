package com.jayus.smallMyBatis.step19.scripting.xmltags;

import com.jayus.smallMyBatis.step19.executor.parameter.ParameterHandler;
import com.jayus.smallMyBatis.step19.mapping.BoundSql;
import com.jayus.smallMyBatis.step19.mapping.MappedStatement;
import com.jayus.smallMyBatis.step19.mapping.SqlSource;
import com.jayus.smallMyBatis.step19.scripting.LanguageDriver;
import com.jayus.smallMyBatis.step19.scripting.defaults.DefaultParameterHandler;
import com.jayus.smallMyBatis.step19.scripting.defaults.RawSqlSource;
import com.jayus.smallMyBatis.step19.session.Configuration;
import org.dom4j.Element;

/**
 * @ClassName XMLLanguageDriver
 * @Description: XML 语言驱动器
 * @date: 2024/10/19 13:38
 */
public class XMLLanguageDriver implements LanguageDriver {

    @Override
    public SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType) {
        XMLScriptBuilder builder = new XMLScriptBuilder(configuration, script, parameterType);
        return builder.parseScriptNode();
    }

    @Override
    public SqlSource createSqlSource(Configuration configuration, String script, Class<?> parameterType) {
        return new RawSqlSource(configuration,script,parameterType);
    }

    @Override
    public ParameterHandler createParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
        return new DefaultParameterHandler(mappedStatement,parameterObject,boundSql);
    }
}
