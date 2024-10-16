package com.jayus.smallMyBatis.step12.session;

import com.jayus.smallMyBatis.step12.builder.xml.XMLConfigBuilder;
import com.jayus.smallMyBatis.step12.session.defaults.DefaultSqlSessionFactory;

import java.io.Reader;

/**
 * @ClassName SqlSessionFactoryBuilder
 * @Description: 构建 SqlSessionFactory 的工厂
 * @date: 2024/9/25 08:39
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader) {
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }

    public SqlSessionFactory build(Configuration config){
        return new DefaultSqlSessionFactory(config);
    }


}
