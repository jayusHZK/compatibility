package com.jayus.smallMyBatis.step10.scripting.xmltags;

/**
 * sql 节点
 */
public interface SqlNode {

    boolean apply(DynamicContext context);

}
