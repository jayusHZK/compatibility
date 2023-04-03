package com.jayus.test.reflect;

import com.jayus.vo.UserVO;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

/**
 * @author : h zk
 * @date : 2023/4/3 15:49
 * @description :
 **/
public class test {

    public static void main(String[] args) {
        UserVO userVO = new UserVO();
        Field[] fields = userVO.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getType());
            System.out.println(field.getGenericType());
            System.out.println(field.getGenericType() instanceof ParameterizedType);
            System.out.println("======");
        }
    }
    
}
