package com.jayus.smallMyBatis.step19.scripting.xmltags;

/**
 * @ClassName IfSqlNode
 * @Description: IF SQL 节点
 * @date: 2024/10/19 14:35
 */
public class IfSqlNode implements SqlNode {

    private ExpressionEvaluator evaluator;

    private String test;

    private SqlNode contents;

    public IfSqlNode(SqlNode contents, String test) {
        this.test = test;
        this.contents = contents;
        this.evaluator = new ExpressionEvaluator();
    }

    @Override
    public boolean apply(DynamicContext context) {
        if (evaluator.evaluateBoolean(test,context.getBindings())){
            contents.apply(context);
            return true;
        }
        return false;
    }
}
