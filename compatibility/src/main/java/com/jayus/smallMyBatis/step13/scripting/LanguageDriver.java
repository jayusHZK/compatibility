package com.jayus.smallMyBatis.step13.scripting;

import com.jayus.smallMyBatis.step13.executor.parameter.ParameterHandler;
import com.jayus.smallMyBatis.step13.mapping.BoundSql;
import com.jayus.smallMyBatis.step13.mapping.MappedStatement;
import com.jayus.smallMyBatis.step13.mapping.SqlSource;
import com.jayus.smallMyBatis.step13.session.Configuration;
import org.dom4j.Element;

/**
 * @ClassName LanguageDriver
 * @Description: 脚本语言驱动
 * @date: 2024/10/13 09:28
 */
public interface LanguageDriver {

    /*
    创建SQL源码(mapper xml方式)
     */
    SqlSource createSqlSource(Configuration configuraction, Element script, Class<?> parameterType);

    /*
    创建SQL源码(annotation 注解方式)
     */
    SqlSource createSqlSource(Configuration configuraction, String script, Class<?> parameterType);

    /*
    创建参数处理器
     */
    ParameterHandler createParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql);
}
