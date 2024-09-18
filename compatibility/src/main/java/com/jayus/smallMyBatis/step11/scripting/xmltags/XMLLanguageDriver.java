package com.jayus.smallMyBatis.step11.scripting.xmltags;

import com.jayus.smallMyBatis.step11.executor.parameter.ParameterHandler;
import com.jayus.smallMyBatis.step11.mapping.BoundSql;
import com.jayus.smallMyBatis.step11.mapping.MappedStatement;
import com.jayus.smallMyBatis.step11.mapping.SqlSource;
import com.jayus.smallMyBatis.step11.scripting.LanguageDriver;
import com.jayus.smallMyBatis.step11.scripting.defaults.DefaultParameterHandler;
import com.jayus.smallMyBatis.step11.session.Configuration;
import org.dom4j.Element;

/**
 * @ClassName XMLLanguageDriver
 * @Description: XML语言驱动器
 * @date: 2024/9/17 22:46
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
