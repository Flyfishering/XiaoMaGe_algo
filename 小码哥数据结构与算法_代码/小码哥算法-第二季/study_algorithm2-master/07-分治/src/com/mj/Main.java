package com.mj;

public class Main {

    public static void main(String args[]) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));
    }

    static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return maxSubArray(nums, 0, nums.length);
    }

    /**
     * 求解[begin, end)中最大连续子序列的和
     * T(n) = T(n/2) + T(n/2) + O(n)
     */
    static int maxSubArray(int[] nums, int begin, int end) {
        if (end - begin < 2) return nums[begin];
        int mid = (begin + end) >> 1;
        int leftMax = Integer.MIN_VALUE, rightMax = Integer.MIN_VALUE;
        int leftSum = 0, rightSum = 0;
        for (int i = mid - 1; i >= begin; i--) {
            leftSum += nums[i];
            leftMax = Math.max(leftMax, leftSum);
        }

        for (int i = mid; i < end; i++) {
            rightSum += nums[i];
            rightMax = Math.max(rightMax, rightSum);
        }

        return Math.max(leftMax + rightMax,
                Math.max(
                        maxSubArray(nums, begin, mid),
                        maxSubArray(nums, mid, end))
        );
    }

    static int maxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        for (int begin = 0; begin < nums.length; begin++) {
            int sum = 0;
            for (int end = begin; end < nums.length; end++) {
                sum += nums[end];
                max = Math.max(sum, max);
            }
        }
        return max;
    }
}
