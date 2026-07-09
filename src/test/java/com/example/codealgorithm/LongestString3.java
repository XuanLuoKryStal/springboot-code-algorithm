package com.example.codealgorithm;

import java.util.HashMap;

public class LongestString3 {
    public static void main(String[] args) {
        LongestString3 foo = new LongestString3();
        System.out.println(foo.lengthOfLongestSubstring("pwwkew"));
    }

    // 滑动窗口的思路，需要确定的是：left第二次移动到相同字符串位置，right只是继续移动+1
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLength = 0;
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (map.containsKey(c) && map.get(c) >= left) {
                left = map.get(c) + 1;
            }
            map.put(c, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }
}
