package com.jayus.leetCode;

import javax.print.attribute.standard.MediaSize;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : h zk
 * @date : 2022/7/5 17:44
 * @description :
 **/
public class Five5 {

    public static void main(String[] args) {
        String str = "ccc";
        System.out.println(longestPalindrome(str));
    }

    public static String longestPalindrome(String str) {
        Map<Character, Integer> map = new HashMap<>();
        Integer min = 0;
        Integer max = 0;
        for (int end = 0; end < str.length(); end++) {
            char alpha = str.charAt(end);
            if (map.containsKey(alpha)) {
                min = map.get(alpha);
                max = end;
                if ((max - min) < (end - map.get(alpha))) {
                    min = map.get(alpha);
                    max = end;
                }
                map.put(alpha, end);
            }
            map.put(alpha, end);
        }
        return str.substring(min, max + 1);
    }

}
