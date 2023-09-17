package com.jayus.smallMyBatis.step06.session;

import com.jayus.smallMyBatis.step06.builder.xml.XMLConfigBuilder;
import com.jayus.smallMyBatis.step06.session.defaults.DefaultSqlSessionFactory;

import java.io.Reader;

public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader){
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }

    public SqlSessionFactory build(Configuration config){
        return new DefaultSqlSessionFactory(config);
    }

}
