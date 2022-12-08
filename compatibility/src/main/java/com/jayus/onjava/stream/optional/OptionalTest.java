package com.jayus.onjava.stream.optional;

import com.jayus.vo.UserVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : h zk
 * @date : 2022/12/1 11:05
 * @description :
 **/
public class OptionalTest {
    public static void main(String[] args) {
        List<UserVO> list = new ArrayList<>();
        UserVO a = new UserVO("a");
        list.add(a);
        list.stream().filter(item -> item.getUsername().equals("c")).findFirst().orElse(null);
        System.out.println();
    }
}
