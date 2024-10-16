package com.jayus.smallMyBatis.step12.script.xmltags;

import java.util.List;

/**
 * @ClassName MixedSqlNode
 * @Description: 混合 SQL 节点
 * @date: 2024/9/26 13:48
 */
public class MixedSqlNode implements SqlNode {

    private List<SqlNode> contents;

    public MixedSqlNode(List<SqlNode> contents) {
        this.contents = contents;
    }

    @Override
    public boolean apply(DynamicContext context) {
        contents.forEach(node -> node.apply(context));
        return true;
    }
}
