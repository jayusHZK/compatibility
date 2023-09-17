package com.jayus.smallMyBatis.step07.session;

import com.jayus.smallMyBatis.step07.builder.xml.XMLConfigBuilder;
import com.jayus.smallMyBatis.step07.session.defaults.defaultSqlSessionFactory;

import java.io.Reader;

public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader){
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }

    public SqlSessionFactory build(Configuration config){
        return new defaultSqlSessionFactory(config);
    }

}
