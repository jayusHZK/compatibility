package com.jayus.smallMyBatis.step19.session;

import com.jayus.smallMyBatis.step19.builder.xml.XMLConfigBuilder;
import com.jayus.smallMyBatis.step19.session.defaults.DefaultSqlSessionFactory;
import org.dom4j.Document;

import java.io.Reader;

/**
 * @ClassName SqlSessionFactoryBuilder
 * @Description: 构建 SqlSessionFactory 的工厂
 * @date: 2024/10/18 11:29
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader) {
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }

    public SqlSessionFactory build(Document document) {
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(document);
        return build(xmlConfigBuilder.parse());
    }

    public SqlSessionFactory build(Configuration configuration) {
        return new DefaultSqlSessionFactory(configuration);
    }

}
