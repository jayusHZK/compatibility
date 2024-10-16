package com.jayus.smallMyBatis.step12.builder.xml;

import com.jayus.smallMyBatis.step12.builder.BaseBuilder;
import com.jayus.smallMyBatis.step12.datasource.DataSourceFactory;
import com.jayus.smallMyBatis.step12.io.Resources;
import com.jayus.smallMyBatis.step12.mapping.Environment;
import com.jayus.smallMyBatis.step12.session.Configuration;
import com.jayus.smallMyBatis.step12.transaction.TransactionFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import javax.sql.DataSource;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;
import java.util.Properties;

/**
 * @ClassName XMLConfigBuilder
 * @Description: XML 配置构建器，建造者模式，继承 BaseBuilder
 * @date: 2024/9/26 14:54
 */
public class XMLConfigBuilder extends BaseBuilder {

    private Element root;

    public XMLConfigBuilder(Reader reader) {
        super(new Configuration());
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(new InputSource(reader));
            root = document.getRootElement();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public Configuration parse(){
        try {
            environmentsElement(root.element("environments"));
            mapperElement(root.element("mappers"));
        } catch (Exception e) {
            throw new RuntimeException("Error parsing SQL Mapper Configuration. Cause: " + e, e);
        }
        return configuration;
    }

    private void environmentsElement(Element context) throws Exception{
        String environment = context.attributeValue("default");
        List<Element> environmentList = context.elements("environment");
        for (Element e : environmentList) {
            String id = e.attributeValue("id");
            if (environment.equals(id)) {
                TransactionFactory txFactory = (TransactionFactory) typeAliasRegistry
                        .resolveAlias(e.element("transactionManager").attributeValue("type")).newInstance();
                Element dataSourceElement = e.element("dataSource");

                DataSourceFactory dataSourceFactory = (DataSourceFactory) typeAliasRegistry
                        .resolveAlias(dataSourceElement.attributeValue("type")).newInstance();
                List<Element> propertyList = dataSourceElement.elements("property");
                Properties props = new Properties();
                for (Element property : propertyList) {
                    props.setProperty(property.attributeValue("name"),property.attributeValue("value"));
                }
                dataSourceFactory.setProperties(props);
                DataSource dataSource = dataSourceFactory.getDataSource();

                Environment.Builder environmentBuilder = new Environment.Builder(id).transactionFactory(txFactory).dataSource(dataSource);
                configuration.setEnvironment(environmentBuilder.build());
            }
        }
    }

    private void mapperElement(Element mappers) throws Exception {
        List<Element> mapperList = mappers.elements("mapper");
        for (Element e : mapperList) {
            String resource = e.attributeValue("resource");
            InputStream inputStream = Resources.getResourceAsStream(resource);
            XMLMapperBuilder mapperParser = new XMLMapperBuilder(inputStream, configuration, resource);
            mapperParser.parse();
        }
    }

}
