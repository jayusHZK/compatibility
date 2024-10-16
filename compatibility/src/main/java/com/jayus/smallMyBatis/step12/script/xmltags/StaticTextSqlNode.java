package com.jayus.smallMyBatis.step12.script.xmltags;

/**
 * @ClassName StaticTextSqlNode
 * @Description: 静态文本 SQL 节点
 * @date: 2024/9/26 13:46
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
