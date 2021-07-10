package com.mj;

// Longest Increasing Subsequence
public class LIS {

    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[] {10, 2, 2, 5, 1, 7, 101, 18}));
    }

    // 模拟扑克牌 + 二分搜索(top数组是有序数组，往里面放元素，自然可以想到二分法)
    static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        // 牌顶数组
        int[] top = new int[nums.length];
        // 牌堆数量
        int len = 0;

        // 遍历所有的牌
        for (int num : nums) {
            // 二分法
            int begin = 0;
            int end = len;
            while (begin < end) {
                int mid = (begin + end) >> 1;
                if (top[mid] >= num) {
                    end = mid;
                } else {
                    begin = mid + 1;
                }
            }

            top[begin] = num;
            if (begin == len) len++;
        }
        return len;
    }

    // 模拟扑克牌
    static int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        // 牌顶数组
        int[] top = new int[nums.length];
        // 牌堆数量
        int len = 0;

        // 遍历所有的牌
        for (int num : nums) {
            int j = 0;
            // 遍历牌顶
            while (j < len) {
                // 若牌顶大于等于选中的牌，则该牌放置在牌顶
                if (top[j] >= num) {
                    top[j] = num;
                    break;
                }
                // 否则找下一个牌堆
                j++;
            }
            // 若该牌没有放在任何牌堆上
            if (j == len) {
                // 创建新的牌堆
                len++;
                top[j] = num;
            }
        }
        return len;
    }

    // dp
    static int lengthOfLIS1(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int[] dp = new int[nums.length];
        int max = dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
