package com.jayus.smallMyBatis.step13.builder.xml;

import com.jayus.smallMyBatis.step13.builder.BaseBuilder;
import com.jayus.smallMyBatis.step13.datasource.DataSourceFactory;
import com.jayus.smallMyBatis.step13.io.Resources;
import com.jayus.smallMyBatis.step13.mapping.Environment;
import com.jayus.smallMyBatis.step13.session.Configuration;
import com.jayus.smallMyBatis.step13.transaction.TransactionFactory;
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
 * @Description: XML配置构建器，建造者模式，继承 #BaseBuilder
 * @date: 2024/10/16 10:49
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
            throw new RuntimeException(e);
        }
    }

    /*
    解析配置：类型别名、插件、对象工厂、对象保证工厂、设置、环境、类型转换、映射器
     */
    public Configuration parse(){
        try {
            environmentsElement(root.element("environments"));
            mapperElement(root.element("mappers"));
        } catch (Exception e) {
            throw new RuntimeException("Error parsing SQL Mapper Configuration. Cause: " + e, e);
        }
        return configuration;
    }

    private void environmentsElement(Element context) throws Exception {
        String environment = context.attributeValue("default");

        List<Element> environmentList = context.elements("environment");
        for (Element e : environmentList) {
            String id = e.attributeValue("id");
            if (environment.equals(id)) {
                TransactionFactory txFactory = (TransactionFactory) typeAliasRegistry.resolveAlias(e.element("transactionManager").attributeValue("type")).newInstance();
                Element dataSourceElement = e.element("dataSource");
                DataSourceFactory dataSourceFactory = (DataSourceFactory) typeAliasRegistry.resolveAlias(dataSourceElement.attributeValue("type")).newInstance();
                List<Element> propertyList = dataSourceElement.elements("property");
                Properties props = new Properties();
                for (Element property : propertyList) {
                    props.setProperty(property.attributeValue("name"),property.attributeValue("value"));
                }
                dataSourceFactory.setProperties(props);
                DataSource dataSource = dataSourceFactory.getDataSource();

                Environment.Builder environmentBuilder = new Environment.Builder(id).transactionFactory(txFactory).setDataSource(dataSource);
                configuration.setEnvironment(environmentBuilder.build());
            }
        }
    }

    private void mapperElement(Element mappers) throws Exception {
        List<Element> mapperList = mappers.elements("mapper");
        for (Element e : mapperList) {
            String resource = e.attributeValue("resource");
            String mapperClass = e.attributeValue("class");
            if (resource != null && mapperClass == null) {
                InputStream inputStream = Resources.getResourceAsStream(resource);
                XMLMapperBuilder mapperParser = new XMLMapperBuilder(inputStream, configuration, resource);
                mapperParser.parse();
            }
            // Annotation 注解解析
            else if (resource == null && mapperClass != null) {
                Class<?> mapperInterface = Resources.classForName(mapperClass);
                configuration.addMapper(mapperInterface);
            }
        }
    }

}
