package com.jayus.smallMyBatis.step13.scripting.xmltags;

/**
 * @ClassName SqlNode
 * @Description: SQL 节点
 * @date: 2024/10/15 21:41
 */
public interface SqlNode {

    boolean apply(DynamicContext context);

}
