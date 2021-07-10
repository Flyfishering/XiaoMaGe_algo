package com.mj;

// 最大连续子序列
public class MaxSubArray {

    // 优化了空间复杂度
    static int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int dp = nums[0];
        int max = dp;
        for (int i = 1; i < nums.length; i++) {
            if (dp <= 0) {
                dp = nums[i];
            } else {
                dp = dp + nums[i];
            }
            max = Math.max(max, dp);
        }
        return max;
    }

    static int maxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] <= 0) {
                dp[i] = nums[i];
            } else {
                dp[i] = dp[i - 1] + nums[i];
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    public static void main(String args[]) {

    }
}
