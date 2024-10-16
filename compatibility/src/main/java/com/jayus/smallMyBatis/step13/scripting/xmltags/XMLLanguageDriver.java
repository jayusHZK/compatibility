package com.jayus.smallMyBatis.step13.scripting.xmltags;

import com.jayus.smallMyBatis.step13.executor.parameter.ParameterHandler;
import com.jayus.smallMyBatis.step13.mapping.BoundSql;
import com.jayus.smallMyBatis.step13.mapping.MappedStatement;
import com.jayus.smallMyBatis.step13.mapping.SqlSource;
import com.jayus.smallMyBatis.step13.scripting.LanguageDriver;
import com.jayus.smallMyBatis.step13.scripting.defaults.DefaultParameterHandler;
import com.jayus.smallMyBatis.step13.scripting.defaults.RawSqlSource;
import com.jayus.smallMyBatis.step13.session.Configuration;
import org.dom4j.Element;

/**
 * @ClassName XMLLanguageDriver
 * @Description: XML语言驱动器
 * @date: 2024/10/15 22:00
 */
public class XMLLanguageDriver implements LanguageDriver {

    @Override
    public SqlSource createSqlSource(Configuration configuraction, Element script, Class<?> parameterType) {
        // 用XML脚本构建器解析
        XMLScriptBuilder builder = new XMLScriptBuilder(configuraction, script, parameterType);
        return builder.parseScriptNode();
    }

    @Override
    public SqlSource createSqlSource(Configuration configuraction, String script, Class<?> parameterType) {
        return new RawSqlSource(configuraction,script,parameterType);
    }

    @Override
    public ParameterHandler createParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
        return new DefaultParameterHandler(mappedStatement,parameterObject,boundSql);
    }
}
