package com.jayus.smallMyBatis.step19.scripting.xmltags;

import com.jayus.smallMyBatis.step19.parsing.GenericTokenParser;
import com.jayus.smallMyBatis.step19.parsing.TokenHandler;
import com.jayus.smallMyBatis.step19.type.SimpleTypeRegistry;

import java.util.regex.Pattern;

/**
 * @ClassName TextSqlNode
 * @Description: 文本 SQL 节点(CDATA | TEXT)
 * @date: 2024/10/19 14:05
 */
public class TextSqlNode implements SqlNode {

    private String text;

    private Pattern injectionFilter;

    public TextSqlNode(String text) {
        this(text,null);
    }

    public TextSqlNode(String text, Pattern injectionFilter) {
        this.text = text;
        this.injectionFilter = injectionFilter;
    }

    /*
    判断是否是动态 sql
     */
    public boolean isDynamic(){
        DynamicCheckerTokenParser checker = new DynamicCheckerTokenParser();
        GenericTokenParser parser = createParser(checker);
        parser.parse(text);
        return checker.isDynamic;
    }

    @Override
    public boolean apply(DynamicContext context) {
        GenericTokenParser parser = createParser(new BindingTokenParser(context, injectionFilter));
        context.appendSql(parser.parse(text));
        return true;
    }

    private GenericTokenParser createParser(TokenHandler handler) {
        return new GenericTokenParser("${","}",handler);
    }

    private static class BindingTokenParser implements TokenHandler {
        private DynamicContext context;
        private Pattern injectionFilter;

        public BindingTokenParser(DynamicContext context, Pattern injectionFilter) {
            this.context = context;
            this.injectionFilter = injectionFilter;
        }

        @Override
        public String handleToken(String content) {
            Object parameter = context.getBindings().get("_parameter");
            if (parameter == null) {
                context.getBindings().put("value",null);
            } else if (SimpleTypeRegistry.isSimpleType(parameter.getClass())) {
                context.getBindings().put("value",parameter);
            }
            Object value = OgnlCache.getValue(content, context.getBindings());
            String strValue = (value == null ? "" : String.valueOf(value));
            checkInjection(strValue);
            return strValue;
        }

        private void checkInjection(String value) {
            if (injectionFilter != null && !injectionFilter.matcher(value).matches()) {
                throw new RuntimeException("Invalid input. Please conform to regex" + injectionFilter.pattern());
            }
        }
    }

    /*
    动态 SQL 检查器
     */
    private static class DynamicCheckerTokenParser implements TokenHandler {
        private boolean isDynamic;

        public DynamicCheckerTokenParser() {
        }

        public boolean isDynamic() {
            return isDynamic;
        }

        @Override
        public String handleToken(String content) {
            this.isDynamic  = true;
            return null;
        }
    }

}
