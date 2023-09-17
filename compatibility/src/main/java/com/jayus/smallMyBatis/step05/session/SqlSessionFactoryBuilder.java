package com.jayus.smallMyBatis.step05.session;

import com.jayus.smallMyBatis.step05.builder.xml.XMLConfigBuilder;
import com.jayus.smallMyBatis.step05.session.defaults.DefaultSqlSessionFactory;

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
