package com.jayus.smallMyBatis.step03.builder.xml;

import com.jayus.smallMyBatis.step03.builder.BaseBuilder;
import com.jayus.smallMyBatis.step03.io.Resources;
import com.jayus.smallMyBatis.step03.mapping.MappedStatement;
import com.jayus.smallMyBatis.step03.mapping.SqlCommandType;
import com.jayus.smallMyBatis.step03.session.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * XML 配置文件解析器
 */
public class XmlConfigBuilder extends BaseBuilder {

    private Element root;

    /**
     * 构造器 读取 xml 文件 获取根节点
     * @param reader
     */
    public XmlConfigBuilder(Reader reader) {
       super(new Configuration());
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(new InputSource(reader));
            root = document.getRootElement();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解析 xml 文件
     * @return
     */
    public Configuration parse(){
        try {
            mapperElement(root.element("mappers"));
        } catch (Exception e) {
            throw new RuntimeException("Error parsing SQL Mapper Configuration. Cause: " + e, e);
        }
        return configuration;
    }

    private void mapperElement(Element mappers) throws Exception {
        // 获取 xml 配置文件中 mappers 标签下的所有 mapper 标签
        List<Element> elements = mappers.elements("mapper");
        for (Element e : elements) {
            // 解析处理每一处 mapper 标签的 resource 属性，该属性用于表示项目路径下 resources 文件夹下的 mapper 文件
            String resource = e.attributeValue("resource");
            // 获取映射 mapper 文件的输入流，使用 dom4j 解析
            Reader reader = Resources.getResourceAsReader(resource);
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new InputSource(reader));
            // 解析 mapper 文件的根节点
            Element root = document.getRootElement();
            // 获取映射器的命名空间
            String namespace = root.attributeValue("namespace");
            // 获取 mapper 文件中的 select 标签
            List<Element> selectElements = root.elements("select");
            for (Element node : selectElements) {
                // 获取接口的方法名
                String id = node.attributeValue("id");
                // 获取接口的返回值类型
                String resultType = node.attributeValue("resultType");
                // 获取接口的参数类型
                String parameterType = node.attributeValue("parameterType");
                // 获取接口的 SQL 语句
                // getTextTrim() 方法用于当前节点的文本内容 去除前后空格
                String sql = node.getTextTrim();
                // 解析 SQL 语句中的 #{} 参数
                Map<Integer,String> parameterMap = new HashMap<>();
                Pattern pattern = Pattern.compile("(#\\{(.*?)})");
                Matcher matcher = pattern.matcher(sql);
                for (int i = 1;matcher.find();i++){
                    // matcher.group(1) 获取的是 #{} 的内容 包括整个 #{}
                    String g1 = matcher.group(1);
                    // matcher.group(2) 获取的是 {} 内部的内容
                    String g2 = matcher.group(2);
                    parameterMap.put(i,g2);
                    sql = sql.replace(g1,"?");
                }
                System.out.println("解析后的SQL语句为："+sql);
                // 将解析后的 SQL 语句和参数信息存入到 MappedStatement 中
                String msId = namespace + "." + id;
                String nodeName = node.getName();
                SqlCommandType sqlCommandType = SqlCommandType.valueOf(nodeName.toUpperCase(Locale.ENGLISH));
                MappedStatement mappedStatement = new MappedStatement.Builder(configuration, msId, sqlCommandType, parameterType, resultType, sql, parameterMap).build();
                configuration.addMappedStatement(mappedStatement);
            }
            configuration.addMapper(Resources.classForName(namespace));
        }
    }
}
