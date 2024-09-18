package com.jayus.smallMyBatis.step11.session;

import com.jayus.smallMyBatis.step11.build.xml.XmlConfigBuilder;
import com.jayus.smallMyBatis.step11.session.defaults.DefaultSqlSessionFactory;

import java.io.Reader;

/**
 * @ClassName SqlSessionFactoryBuilder
 * @Description: 构建 SqlSessionFactory 的工厂
 * @date: 2024/9/18 21:20
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader){
        XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }

    public SqlSessionFactory build(Configuration config) {
        return new DefaultSqlSessionFactory(config);
    }

}
