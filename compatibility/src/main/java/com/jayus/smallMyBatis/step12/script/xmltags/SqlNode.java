package com.jayus.smallMyBatis.step12.script.xmltags;

/**
 * @ClassName SqlNode
 * @Description: SQL 节点
 * @date: 2024/9/26 00:54
 */
public interface SqlNode {

    boolean apply(DynamicContext context);

}
