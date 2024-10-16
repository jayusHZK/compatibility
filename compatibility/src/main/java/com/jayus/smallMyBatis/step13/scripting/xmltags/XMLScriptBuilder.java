package com.jayus.smallMyBatis.step13.scripting.xmltags;

import com.jayus.smallMyBatis.step13.builder.BaseBuilder;
import com.jayus.smallMyBatis.step13.mapping.SqlSource;
import com.jayus.smallMyBatis.step13.scripting.defaults.RawSqlSource;
import com.jayus.smallMyBatis.step13.session.Configuration;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName XMLScriptBuilder
 * @Description: XML脚本构建器
 * @date: 2024/10/15 22:02
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
        return new RawSqlSource(configuration,rootSqlNode,parameterType);
    }

    List<SqlNode> parseDynamicTags(Element element){
        List<SqlNode> contents = new ArrayList<>();
        String data = element.getText();
        contents.add(new StaticTextSqlNode(data));
        return contents;
    }

}
