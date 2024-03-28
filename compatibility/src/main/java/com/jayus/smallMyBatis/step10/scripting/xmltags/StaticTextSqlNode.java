package com.jayus.smallMyBatis.step10.scripting.xmltags;

/**
 * 静态文本 sql 节点
 */
public class StaticTextSqlNode implements SqlNode {

    private String text;

    public StaticTextSqlNode(String text) {
        this.text = text;
    }

    @Override
    public boolean apply(DynamicContext context) {
        context.appendSql(text);
        return true;
    }
}
