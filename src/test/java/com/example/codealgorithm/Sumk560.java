package com.example.codealgorithm;

public class Sumk560 {

    public int subarraySum(int[] nums, int k) {
        int preNum = 0;
        int count = 0;
        java.util.Map<Integer, Integer> map = new java.util.HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            preNum += nums[i];
            if (map.containsKey(preNum - k)) {
                count += map.get(preNum - k);
            }
            map.put(preNum, map.getOrDefault(preNum, 0) + 1);
        }
        return count;
    }

}
