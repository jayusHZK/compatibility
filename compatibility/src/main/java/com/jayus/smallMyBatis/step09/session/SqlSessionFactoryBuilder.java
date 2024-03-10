package com.jayus.smallMyBatis.step09.session;

import com.jayus.smallMyBatis.step09.builder.xml.XmlConfigBuilder;
import com.jayus.smallMyBatis.step09.session.defaults.DefaultSqlSessionFactory;

import java.io.Reader;

/**
 * 构建 SqlSessionFactory 的工厂
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader){
        XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }

    public SqlSessionFactory build(Configuration configuration){
        return new DefaultSqlSessionFactory(configuration);
    }

}
