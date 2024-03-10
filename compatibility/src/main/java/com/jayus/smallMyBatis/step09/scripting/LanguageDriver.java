package com.jayus.smallMyBatis.step09.scripting;

import com.jayus.smallMyBatis.step09.executor.parameter.ParameterHandler;
import com.jayus.smallMyBatis.step09.mapping.BoundSql;
import com.jayus.smallMyBatis.step09.mapping.MappedStatement;
import com.jayus.smallMyBatis.step09.mapping.SqlSource;
import com.jayus.smallMyBatis.step09.session.Configuration;
import org.dom4j.Element;

/**
 * 脚本语言驱动
 */
public interface LanguageDriver {

    /**
     * 创建 SQL 源码
     * @param configuration
     * @param script
     * @param parameterType
     * @return
     */
    SqlSource createSqlSource(Configuration configuration, Element script,Class<?> parameterType);

    /**
     * 创建参数处理器
     * @param mappedStatement
     * @param parameterObject
     * @param boundSql
     * @return
     */
    ParameterHandler createParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql);

}
