package com.jayus.guava;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;
import com.jayus.vo.UserVO;

public class ClassToinstanceMap {
    // 键是class 值是对于的实例对象
    public static void main(String[] args) {
        ClassToInstanceMap<Object> instanceMap = MutableClassToInstanceMap.create();
        UserVO userVO = new UserVO("qiu");
        instanceMap.put(UserVO.class,userVO);
        System.out.println(instanceMap.get(UserVO.class));
        instanceMap.getInstance(UserVO.class);

    }
}
