package com.jayus.leetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : h zk
 * @date : 2022/7/5 15:07
 * @description : 找出字符串中不重复的最大长度
 **/
public class Three3 {
    public static void main(String[] args) {
        String str = "aabcbcabbba";
        System.out.println(lengthOfLongestSubstringLeet(str));
    }

    public static int lengthOfLongestSubstring(String str) {
        int length = 0;
        int max = 0;
        byte[] bytes = str.getBytes();
        List<Byte> byteList = new ArrayList<>();
        for (int i = 0; i < bytes.length; i++) {
            if (!byteList.contains(bytes[i])) {
                length++;
                byteList.add(bytes[i]);
            } else {
                max = length;
                length = 0;
                byteList = new ArrayList<>();
            }
        }
        return max;
    }

    /**
     * 记录字符第二次出现的位置 以此来获得最大次数
     * @param str
     * @return
     */
    public static int lengthOfLongestSubstringLeet(String str) {
        int n = str.length(), max = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int end = 0, start = 0; end < n; end++) {
            char alpha = str.charAt(end);
            if (map.containsKey(alpha)) {
                start = Math.max(map.get(alpha), start);
            }
            max = Math.max(max, end - start + 1);
            map.put(str.charAt(end), end + 1);
        }
        return max;
    }

}
