package com.jayus.test;

import cn.hutool.core.util.RandomUtil;
import com.jayus.vo.UserVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : h zk
 * @date : 2022/10/31 15:25
 * @description :
 **/
public class StreamMapTest {
    public static void main(String[] args) {
        long a = 1;
        System.out.println(new Long(1).equals(1L));
        List<UserVO> list = new ArrayList<>();
        list.add(new UserVO("a"));
        list.add(new UserVO("a"));
        list.add(new UserVO("b"));
        list.add(new UserVO("b"));
        Map<String, List<UserVO>> collect = list.stream().collect(Collectors.groupingBy(UserVO::getUsername));
        for (Map.Entry<String, List<UserVO>> item : collect.entrySet()) {
            System.out.println(item.getKey());
            System.out.println(item.getValue());
            System.out.println("---------");
        }
    }
}
