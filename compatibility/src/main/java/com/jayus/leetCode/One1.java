package com.jayus.leetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : h zk
 * @date : 2022/7/1 17:39
 * @description : 从一个数组中拿到相加等于指定数字的下标
 **/
public class One1 {

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1,5,9,4,6,7};
        try {
            System.out.println(Arrays.asList(getIndexs(arr, 10)));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    //
    public static Integer[] getIndexs(Integer[] arr,int sum) throws IllegalAccessException {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(sum-arr[i])) {
                return new Integer[]{i,map.get(sum-arr[i])};
            }
            //存入当前值和下标
            map.put(arr[i],i);
        }
        throw new IllegalAccessException("no two num sulotion");
    }

}
