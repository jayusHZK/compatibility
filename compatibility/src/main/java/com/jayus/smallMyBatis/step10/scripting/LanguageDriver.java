package com.jayus.smallMyBatis.step10.scripting;

import com.jayus.smallMyBatis.step10.executor.parameter.ParameterHandler;
import com.jayus.smallMyBatis.step10.mapping.BoundSql;
import com.jayus.smallMyBatis.step10.mapping.MappedStatement;
import com.jayus.smallMyBatis.step10.mapping.SqlSource;
import com.jayus.smallMyBatis.step10.session.Configuration;
import org.dom4j.Element;

/**
 * 脚本语言驱动
 */
public interface LanguageDriver {

    /**
     * 创建SQL源码(mapper xml方式)
     */
    SqlSource createSqlSource(Configuration configuration, Element script,Class<?> parameterType);

    /**
     * 创建参数处理器
     */
    ParameterHandler createParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql);

}
