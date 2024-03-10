package com.jayus.smallMyBatis.step08.scripting.xmltags;

/**
 * SQL 节点
 */
public interface SqlNode {

    boolean apply(DynamicContext context);

}
