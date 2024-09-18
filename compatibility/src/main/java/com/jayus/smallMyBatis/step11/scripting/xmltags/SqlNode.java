package com.jayus.smallMyBatis.step11.scripting.xmltags;

/**
 * @ClassName SqlNode
 * @Description: sql 节点
 * @date: 2024/9/17 23:43
 */
public interface SqlNode {

    boolean apply(DynamicContext context);

}
