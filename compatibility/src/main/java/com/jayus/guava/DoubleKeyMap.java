package com.jayus.guava;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;

import java.util.Collection;
import java.util.Set;

public class DoubleKeyMap {
    // 坐标图，类似x轴和y轴的概念 前两个元素定位后一个元素
    public static void main(String[] args) {
        Table<String,String,String> table = HashBasedTable.create();
        // 插入
        table.put("qiu","min","gou");
        table.put("qi","lin","zhu");
        // 查询
        System.out.println(table.get("qi", "lin"));
        // 获取rowKey和columeKey 集合
        Set<String> strings = table.rowKeySet();
        Set<String> strings1 = table.columnKeySet();
        Collection<String> values = table.values();
        // 转换 rowKey和columnKey
        Table<String, String, String> transpose = Tables.transpose(table);

    }
}
