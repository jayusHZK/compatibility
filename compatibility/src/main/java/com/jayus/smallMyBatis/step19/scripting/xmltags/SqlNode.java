package com.jayus.smallMyBatis.step19.scripting.xmltags;

/**
 * @ClassName SqlNode
 * @Description: SQL 节点
 * @date: 2024/10/19 13:49
 */
public interface SqlNode {

    boolean apply(DynamicContext context);

}
