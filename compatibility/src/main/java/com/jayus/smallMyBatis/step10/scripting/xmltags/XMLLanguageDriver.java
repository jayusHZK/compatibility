package com.jayus.smallMyBatis.step10.scripting.xmltags;

import com.jayus.smallMyBatis.step10.executor.parameter.ParameterHandler;
import com.jayus.smallMyBatis.step10.mapping.BoundSql;
import com.jayus.smallMyBatis.step10.mapping.MappedStatement;
import com.jayus.smallMyBatis.step10.mapping.SqlSource;
import com.jayus.smallMyBatis.step10.scripting.LanguageDriver;
import com.jayus.smallMyBatis.step10.scripting.defaults.DefaultParameterHandler;
import com.jayus.smallMyBatis.step10.session.Configuration;
import org.dom4j.Element;

/**
 * XML 语言驱动器
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
