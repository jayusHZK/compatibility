package com.jayus.smallMyBatis.step10.scripting.xmltags;

import com.jayus.smallMyBatis.step10.executor.parameter.ParameterHandler;
import com.jayus.smallMyBatis.step10.mapping.BoundSql;
import com.jayus.smallMyBatis.step10.mapping.SqlSource;
import com.jayus.smallMyBatis.step10.scripting.LanguageDriver;
import com.jayus.smallMyBatis.step10.session.Configuration;
import com.jayus.smallMyBatis.step10.session.MappedStatement;
import org.dom4j.Element;

/**
 * XML 语言驱动器
 */
public class XMLLanguageDriver implements LanguageDriver {

    @Override
    public SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType) {
        new xml
        return null;
    }

    @Override
    public ParameterHandler createParameterhandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
        return null;
    }
}
