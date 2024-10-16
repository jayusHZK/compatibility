package com.jayus.smallMyBatis.step13.session;

import com.jayus.smallMyBatis.step13.builder.xml.XMLConfigBuilder;
import com.jayus.smallMyBatis.step13.session.defaults.DefaultSqlSessionFactory;

import java.io.Reader;

/**
 * @ClassName SqlSessionFactoryBuilder
 * @Description: 构建 SqlSessionFactory的工厂
 * @date: 2024/10/16 10:48
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader) {
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }

    public SqlSessionFactory build(Configuration config) {
        return new DefaultSqlSessionFactory(config);
    }

}
