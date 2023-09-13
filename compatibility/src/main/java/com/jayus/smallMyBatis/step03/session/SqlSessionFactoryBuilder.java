package com.jayus.smallMyBatis.step03.session;

import com.jayus.smallMyBatis.step03.builder.xml.XmlConfigBuilder;
import com.jayus.smallMyBatis.step03.session.defaults.DefaultSqlSessionFactory;

import java.io.Reader;

public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader){
        XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }

    public SqlSessionFactory build(Configuration config){
        return new DefaultSqlSessionFactory(config);
    }


}
