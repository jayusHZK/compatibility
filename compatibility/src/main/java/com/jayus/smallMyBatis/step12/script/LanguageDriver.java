package com.jayus.smallMyBatis.step12.script;

import com.jayus.smallMyBatis.step12.executor.parameter.ParameterHandler;
import com.jayus.smallMyBatis.step12.mapping.BoundSql;
import com.jayus.smallMyBatis.step12.mapping.MappedStatement;
import com.jayus.smallMyBatis.step12.mapping.SqlSource;
import com.jayus.smallMyBatis.step12.session.Configuration;
import org.dom4j.Element;

/**
 * @ClassName LanguageDriver
 * @Description: 脚本驱动语言
 * @date: 2024/9/25 09:26
 */
public interface LanguageDriver {

    /*
    创建SQL源码(mapper xml方式)
     */
    SqlSource createSqlSource(Configuration configuration, Element script,Class<?> parameterType);

    ParameterHandler createParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql);

}
