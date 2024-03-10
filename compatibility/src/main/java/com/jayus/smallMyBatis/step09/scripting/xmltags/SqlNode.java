package com.jayus.smallMyBatis.step09.scripting.xmltags;

/**
 * SQL 节点
 */
public interface SqlNode {

    boolean apply(DynamicContext context);

}
