package com.jayus.smallMyBatis.step13.reflection.property;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * @ClassName PropertyTokenizer
 * @Description: 属性分解标记
 * @date: 2024/10/11 15:46
 */
public class PropertyTokenizer implements Iterable<PropertyTokenizer>, Iterator<PropertyTokenizer> {

    private String name;

    private String indexedName;

    private String index;

    private String children;

    public PropertyTokenizer(String fullname) {
        int delim = fullname.indexOf('.');
        if (delim > -1) {
            name = fullname.substring(0,delim);
            children = fullname.substring(delim+1);
        } else {
            name = fullname;
            children = null;
        }
        indexedName = name;
        delim = name.indexOf('[');
        if (delim > -1){
            index = name.substring(delim + 1,name.length() -1);
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
