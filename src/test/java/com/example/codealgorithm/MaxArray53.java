package com.example.codealgorithm;

import java.util.HashMap;
import java.util.Map;

public class MaxArray53 {
    public static void main(String[] args) {
        int[] nums = { 2, 1, -3, 4, -1, 2, 1, -5, 4 };
        System.out.println(maxSubArray(nums));
    }

    public static int maxSubArray(int[] nums) {
        int maxt = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int s = 0;
            for (int j = i + 1; j < nums.length; j++) {
                s += nums[j];
                maxt = Math.max(maxt, s);
            }
        }
        return maxt;
    }


        public  int maxSubArray1(int[] nums) {
       int result = nums[0];
       int current = nums[0];
       for (int i = 1; i < nums.length; i++) {
           current = Math.max(nums[i], current + nums[i]);
           result = Math.max(result, current);
       }
       return result;
    }
}
