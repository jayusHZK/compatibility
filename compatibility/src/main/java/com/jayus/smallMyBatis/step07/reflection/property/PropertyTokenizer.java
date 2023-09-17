package com.jayus.smallMyBatis.step07.reflection.property;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * 属性分词器
 */
public class PropertyTokenizer implements Iterable<PropertyTokenizer>, Iterator<PropertyTokenizer> {

    // 当前字符串
    private String name;

    // 索引名称
    private String indexedName;

    // 索引位置
    private String index;

    // 子属性
    private String children;

    public static void main(String[] args) {
        System.out.println(new PropertyTokenizer("班级[0].学生.成绩"));
    }

    public PropertyTokenizer(String fullname) {
        // 获取第一个 . 的位置
        int delim = fullname.indexOf('.');
        if (delim > -1) {
            // 如果有 . 那么就把 . 前面的字符串赋值给name
            name = fullname.substring(0, delim);
            children = fullname.substring(delim + 1);
        } else {
            // 如果没有 . 那么就把 fullname 赋值给 name
            name = fullname;
            children = null;
        }
        // 把 name 赋值给 indexedName
        indexedName = name;
        // 获取 [ 的位置
        delim = name.indexOf('[');
        if (delim > -1) {
            // 把 [] 之间的数字赋值给 index
            index = name.substring(delim + 1, name.length() - 1);
            // 如果有 [ 那么就把 [ 前面的字符串赋值给 name
            name = name.substring(0, delim);
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
        return this.children != null;
    }

    @Override
    public PropertyTokenizer next() {
        return new PropertyTokenizer(children);
    }

    @Override
    public String toString() {
        return "PropertyTokenizer{" +
                "name='" + name + '\'' +
                ", indexedName='" + indexedName + '\'' +
                ", index='" + index + '\'' +
                ", children='" + children + '\'' +
                '}';
    }
}
