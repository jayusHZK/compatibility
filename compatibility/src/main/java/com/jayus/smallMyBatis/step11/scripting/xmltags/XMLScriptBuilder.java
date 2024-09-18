package com.jayus.smallMyBatis.step11.scripting.xmltags;

import com.jayus.smallMyBatis.step11.build.BaseBuilder;
import com.jayus.smallMyBatis.step11.mapping.SqlSource;
import com.jayus.smallMyBatis.step11.scripting.defaults.RawSQLSource;
import com.jayus.smallMyBatis.step11.session.Configuration;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName XMLScriptBuilder
 * @Description: XML脚本构建器
 * @date: 2024/9/17 22:47
 */
public class XMLScriptBuilder extends BaseBuilder {

    private Element element;
    private boolean isDynamic;

    private Class<?> parameterType;

    public XMLScriptBuilder(Configuration configuration, Element element, Class<?> parameterType) {
        super(configuration);
        this.element = element;
        this.parameterType = parameterType;
    }

    public SqlSource parseScriptNode(){
        List<SqlNode> contents = parseDynamicTags(element);
        MixedSqlNode rootSqlNode = new MixedSqlNode(contents);
        return new RawSQLSource(configuration,rootSqlNode,parameterType);
    }

    List<SqlNode> parseDynamicTags(Element element) {
        List<SqlNode> contents = new ArrayList<>();
        // element.getText 拿到 sql
        String data = element.getText();
        contents.add(new StaticTextSqlNode(data));
        return contents;
    }
}
