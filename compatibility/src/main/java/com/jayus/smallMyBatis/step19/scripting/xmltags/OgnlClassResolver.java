package com.jayus.smallMyBatis.step19.scripting.xmltags;

import com.jayus.smallMyBatis.step19.io.Resources;
import ognl.ClassResolver;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName OgnlClassResolver
 * @Description:
 * @date: 2024/10/19 14:21
 */
public class OgnlClassResolver implements ClassResolver {

    private Map<String,Class<?>> classes = new HashMap<>(101);

    @Override
    public Class classForName(String className, Map context) throws ClassNotFoundException {
        Class<?> result = null;
        if ((result = classes.get(className)) == null) {
            try {
                result = Resources.classForName(className);
            } catch (ClassNotFoundException e1) {
                if (className.indexOf('.') == -1) {
                    result = Resources.classForName("java.lang." + className);
                    classes.put("java.lang." + className, result);
                }
            }
            classes.put(className, result);
        }
        return result;
    }
}
