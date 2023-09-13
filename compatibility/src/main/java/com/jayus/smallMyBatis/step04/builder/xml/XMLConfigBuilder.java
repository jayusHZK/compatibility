package com.jayus.smallMyBatis.step04.builder.xml;

import com.jayus.smallMyBatis.step04.builder.BaseBuilder;
import com.jayus.smallMyBatis.step04.datasource.DataSourceFactory;
import com.jayus.smallMyBatis.step04.io.Resources;
import com.jayus.smallMyBatis.step04.mapping.BoundSql;
import com.jayus.smallMyBatis.step04.mapping.Environment;
import com.jayus.smallMyBatis.step04.mapping.MappedStatement;
import com.jayus.smallMyBatis.step04.mapping.SqlCommandType;
import com.jayus.smallMyBatis.step04.session.Configuration;
import com.jayus.smallMyBatis.step04.transaction.TransactionFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import javax.sql.DataSource;
import java.io.Reader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLConfigBuilder extends BaseBuilder {

    private Element root;

    public XMLConfigBuilder(Reader reader) {
        // 调用父类构造器
        super(new Configuration());
        //使用dom4j解析xml配置文件
        SAXReader saxReader = new SAXReader();
        // 读取xml文件
        try {
            Document document = saxReader.read(new InputSource(reader));
            //获取根节点
            root = document.getRootElement();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public Configuration parse() {
        try {
            // 解析xml配置文件中的environments
            environmentsElement(root.element("environments"));
            // 解析xml配置文件中的映射器mapper
            mapperElement(root.element("mappers"));
        } catch (Exception e) {
            throw new RuntimeException("Error parsing SQL Mapper Configuration. Cause: " + e, e);
        }
        return configuration;
    }

    private void mapperElement(Element mappers) throws Exception {
        //获取xml配置文件中mappers标签下所有的mapper标签
        List<Element> elements = mappers.elements("mapper");
        //遍历mapper标签
        for(Element e : elements){
            //解析处理每一个mapper标签的resource属性,该属性用于表示项目路径下resources文件夹下的mapper文件
            String resource = e.attributeValue("resource");
            //获取映射mapper文件的输入流，使用dom4j读取解析
            Reader reader = Resources.getResourceAsReader(resource);
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new InputSource(reader));
            //获取mapper文件中的根节点
            Element root = document.getRootElement();
            //获取映射器的命名空间
            String namespace = root.attributeValue("namespace");

            //获取mapper文件中的select标签
            List<Element> selectElements = root.elements("select");
            for(Element node : selectElements){
                //获取接口的方法名
                String id = node.attributeValue("id");
                //获取接口的返回值类型
                String resultType = node.attributeValue("resultType");
                //获取接口的参数类型
                String parameterType = node.attributeValue("parameterType");
                //获取接口的SQL语句
                //getTextTrim()方法用于获取当前节点的文本内容，去除前后空格
                String sql = node.getTextTrim();

                //解析SQL语句中的#{}参数
                Map<Integer,String> parameterMap = new HashMap<>();
                Pattern pattern = Pattern.compile("(#\\{(.*?)})");
                Matcher matcher = pattern.matcher(sql);
                //遍历所有的#{}参数,并将参数替换成?
                for(int i = 1;matcher.find();i++){
                    //matcher.group(1)获取的是#{}的内容,包括整个#{}
                    String g1 = matcher.group(1);
                    //matcher.group(2)获取的是{}内部的内容
                    String g2 = matcher.group(2);
                    //将参数名和参数顺序存入map中
                    parameterMap.put(i,g2);
                    //将#{}替换成?
                    sql = sql.replace(g1,"?");
                }
                System.out.println("解析后的SQL语句为："+sql);

                //将解析后的SQL语句和参数信息存入到MappedStatement中
                String msId = namespace + "." + id;
                String nodeName = node.getName();
                SqlCommandType sqlCommandType = SqlCommandType.valueOf(nodeName.toUpperCase(Locale.ENGLISH));
                //创建BoundSql对象
                BoundSql boundSql = new BoundSql(sql, parameterMap, parameterType, resultType);
                MappedStatement mappedStatement = new MappedStatement.Builder(configuration, msId, sqlCommandType, boundSql).build();
                // 添加解析后的 SQL到 核心配置中
                configuration.addMappedStatement(mappedStatement);
            }
            //添加映射mapper的dao接口到核心配置中
            configuration.addMapper(Resources.classForName(namespace));
        }
    }

    // 解析 xml 配置文件中的 environments
    private void environmentsElement(Element context) throws Exception {
        // 获取默认的数据库类型
        String enviroment = context.attributeValue("default");

        List<Element> elements = context.elements("environment");
        for (Element e : elements) {
            // 获取 environment 标签的 id 属性 表示该环境的数据库类型  配置多环境使用 如 使用 mysql 和 oracle
            String id = e.attributeValue("id");
            if (enviroment.equals(id)) {
                // 获取 xml 配置文件中的事务管理器
                Element txElement = e.element("transactionManager");
                TransactionFactory txFactory = (TransactionFactory) configuration.getTypeAliasRegistry()
                        .resolveAlias(txElement.attributeValue("type")).newInstance();
                // 获取 xml 配置文件中的数据源工厂
                Element dataSourceElement = e.element("dataSource");
                // 获取数据源工厂
                DataSourceFactory dataSourceFactory = (DataSourceFactory) configuration.getTypeAliasRegistry()
                        .resolveAlias(dataSourceElement.attributeValue("type")).newInstance();
                List<Element> propertyElements = dataSourceElement.elements("property");
                Properties properties = new Properties();
                for (Element propertyElement : propertyElements) {
                    String name = propertyElement.attributeValue("name");
                    String value = propertyElement.attributeValue("value");
                    properties.setProperty(name, value);
                }
                dataSourceFactory.setProperties(properties);
                DataSource dataSource = dataSourceFactory.getDataSource();
                Environment.Builder environmentBuilder = new Environment.Builder(id).transactionFactory(txFactory).dataSource(dataSource);
                configuration.setEnvironment(environmentBuilder.build());
            }
        }
    }

}
