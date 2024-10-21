package com.jayus.smallMyBatis.step19.scripting;

import com.jayus.smallMyBatis.step19.executor.parameter.ParameterHandler;
import com.jayus.smallMyBatis.step19.mapping.BoundSql;
import com.jayus.smallMyBatis.step19.mapping.MappedStatement;
import com.jayus.smallMyBatis.step19.mapping.SqlSource;
import com.jayus.smallMyBatis.step19.session.Configuration;
import org.dom4j.Element;

/**
 * @ClassName LanguageDriver
 * @Description: 脚本语言驱动
 * @date: 2024/10/18 11:30
 */
public interface LanguageDriver {

    /*
    创建SQL源码(mapper xml方式)
     */
    SqlSource createSqlSource(Configuration configuration, Element script,Class<?> parameterType);

    /*
    创建SQL源码(annotation 注解方式)
     */
    SqlSource createSqlSource(Configuration configuration,String script,Class<?> parameterType);

    /*
    创建参数处理器
     */
    ParameterHandler createParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql);
}
