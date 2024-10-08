package com.jayus.smallMyBatis.step11.parsing;

/**
 * @ClassName GenericTokenParser
 * @Description: 普通记号解析器，处理 #{} 和 ${} 参数
 * @date: 2024/9/18 01:33
 */
public class GenericTokenParser {

    private final String openToken;

    private final String closeToken;

    private final TokenHandler handler;

    public GenericTokenParser(String openToken, String closeToken, TokenHandler handler) {
        this.openToken = openToken;
        this.closeToken = closeToken;
        this.handler = handler;
    }

    public String parse(String text) {
        StringBuilder builder = new StringBuilder();
        if (text != null && text.length() > 0) {
            char[] src = text.toCharArray();
            int offset = 0;
            int start = text.indexOf(openToken, offset);
            // 这里是循环解析参数，参考GenericTokenParserTest,比如可以解析${first_name} ${initial} ${last_name} reporting.这样的字符串,里面有3个${}
            while (start > -1) {
                if (start > 0 && src[start - 1] == '\\'){
                    builder.append(src,offset,start - offset - 1).append(openToken);
                    offset = start + openToken.length();
                } else {
                    int end = text.indexOf(closeToken, start);
                    if (end == -1) {
                        builder.append(src,offset,src.length - offset);
                        offset = src.length;
                    } else {
                        builder.append(src,offset,start - offset);
                        offset = start + openToken.length();
                        String content = new String(src, offset, end - offset);
                        // 得到一对大括号里的字符串后，调用handler.handleToken,比如替换变量这种功能
                        builder.append(handler.handleToken(content));
                        offset = end + content.length();
                    }
                }
                start = text.indexOf(openToken,offset);
            }
            if (offset < src.length) {
                builder.append(src,offset,src.length - offset);
            }
        }
        return builder.toString();
    }
}
