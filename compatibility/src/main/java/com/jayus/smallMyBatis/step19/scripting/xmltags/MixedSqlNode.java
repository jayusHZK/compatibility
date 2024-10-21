package com.jayus.smallMyBatis.step19.scripting.xmltags;

import java.util.List;

/**
 * @ClassName MixedSqlNode
 * @Description: 混合 SQL 节点
 * @date: 2024/10/19 14:33
 */
public class MixedSqlNode implements SqlNode {

    //组合模式，拥有一个SqlNode的List
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
