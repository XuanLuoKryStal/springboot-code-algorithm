package com.example.codealgorithm;

import java.util.ArrayList;

public class MoveZero283 {
    public static void main(String[] args) {
        int[] nums = new int[] { 0, 1, 0, 3, 12 };
        MoveZero283 foo = new MoveZero283();
        foo.moveZeroes2(nums);
        System.out.println("nums = " + java.util.Arrays.toString(nums));
    }

    // 用空间换时间
    public void moveZeroes(int[] nums) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i : nums) {
            if (i != 0) {
                arrayList.add(i);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (i < arrayList.size()) {
                nums[i] = arrayList.get(i);
            } else {
                nums[i] = 0;
            }
        }
    }

    // 函数功能跟moveZeroes相同，但是用两个指针以及位置交换实现
    public void moveZeroes1(int[] nums) {
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            if (nums[right] != 0) {
                nums[left] = nums[right];
                left++;
            }
            right++;
        }
        while (left < nums.length) {
            nums[left] = 0;
            left++;
        }
    }

    public void moveZeroes2(int[] nums) {
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            if (nums[right] != 0) {
                nums[left] = nums[right];
                left++;
            }
            right++;
        }
    }

}
