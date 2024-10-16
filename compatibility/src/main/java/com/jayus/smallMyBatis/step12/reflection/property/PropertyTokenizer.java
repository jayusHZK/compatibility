package com.jayus.smallMyBatis.step12.reflection.property;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * @ClassName PropertyTokenizer
 * @Description: 属性分解标记
 * @date: 2024/9/19 21:05
 */
public class PropertyTokenizer implements Iterable<PropertyTokenizer>, Iterator<PropertyTokenizer> {

    private String name;

    private String indexedName;

    private String index;

    private String children;

    public PropertyTokenizer(String fullName) {
        int delim = fullName.indexOf('.');
        if (delim > -1) {
            name = fullName.substring(0, delim);
            children = fullName.substring(delim + 1);
        } else {
            // 找不到.的话，取全部部分
            name = fullName;
            children = null;
        }
        indexedName = name;
        // 把中括号里的数字给解析出来
        delim = name.indexOf('[');
        if (delim > -1) {
            index = name.substring(delim + 1, name.length() - 1);
            name = name.substring(0,delim);
        }
    }

    public String getName() {
        return name;
    }

    public String getIndexedName() {
        return indexedName;
    }

    public String getIndex() {
        return index;
    }

    public String getChildren() {
        return children;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Remove is not supported, as it has no meaning in the context of properties.");
    }

    @NotNull
    @Override
    public Iterator<PropertyTokenizer> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return children != null;
    }

    @Override
    public PropertyTokenizer next() {
        return new PropertyTokenizer(children);
    }
}
