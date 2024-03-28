package com.jayus.smallMyBatis.step10.scripting.xmltags;

import java.util.List;

/**
 * 混合 sql 节点
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
