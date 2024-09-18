package com.jayus.smallMyBatis.step11.scripting.xmltags;

import java.util.List;

/**
 * @ClassName MixedSqlNode
 * @Description: 混合 SQL 节点
 * @date: 2024/9/18 00:30
 */
public class MixedSqlNode implements SqlNode {

    // 组合模式，拥有一个 sqlnode 的 list
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
