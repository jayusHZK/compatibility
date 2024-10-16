package com.jayus.smallMyBatis.step13.scripting.xmltags;

import java.util.List;

/**
 * @ClassName MixedSqlNode
 * @Description: 混合 SQL 节点
 * @date: 2024/10/15 22:07
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
