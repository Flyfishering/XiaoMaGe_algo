package com.mj;

// Longest Common Subsequence
public class LCS {

    // dp + 滚动数组(一维数组基础上继续优化。挑length小的作为列(j))
    static int lcs(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) return 0;
        if (nums2 == null || nums2.length == 0) return 0;
        int[] cols, rows;
        if (nums1.length > nums2.length) {
            cols = nums2;
            rows = nums1;
        } else {
            cols = nums1;
            rows = nums2;
        }

        int[] dp = new int[cols.length + 1];

        for (int i = 1; i <= rows.length; i++) {
            int cur = 0;
            for (int j = 1; j <= cols.length; j++) {
                int leftTop = cur;
                cur = dp[j];
                if (rows[i - 1] == cols[j - 1]) {
                    dp[j] = leftTop + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
            }
        }

        return dp[cols.length];
    }

    // dp + 滚动数组(进一步优化，用一维数组)
    static int lcs4(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) return 0;
        if (nums2 == null || nums2.length == 0) return 0;

        int[] dp = new int[nums2.length + 1];

        for (int i = 1; i <= nums1.length; i++) {
            int cur = 0;
            for (int j = 1; j <= nums2.length; j++) {
                int leftTop = cur;
                cur = dp[j];
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[j] = leftTop + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
            }
        }

        return dp[nums2.length];
    }

    // dp + 滚动数组(长度为2的二维数组)
    static int lcs3(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) return 0;
        if (nums2 == null || nums2.length == 0) return 0;

        int[][] dp = new int[2][nums2.length + 1];

        for (int i = 1; i <= nums1.length; i++) {
            // 滚动数组，模上数组长度。模2，正好可以用位运算，与1
            int row = i & 1;
            int prevRow = (i - 1) & 1;
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[row][j] = dp[prevRow][j - 1] + 1;
                } else {
                    dp[row][j] = Math.max(dp[prevRow][j], dp[row][j - 1]);
                }
            }
        }

        return dp[nums1.length & 1][nums2.length];
    }

    // dp(空间复杂度是 O(n*m))
    static int lcs2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) return 0;
        if (nums2 == null || nums2.length == 0) return 0;

        int[][] dp = new int[nums1.length + 1][nums2.length + 1];

        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[nums1.length][nums2.length];
    }

    // 暴力递归
    static int lcs1(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) return 0;
        if (nums2 == null || nums2.length == 0) return 0;
        return lcs1(nums1, nums1.length, nums2, nums2.length);
    }

    /**
     * 求nums1前i个元素和nums2前j个元素的最长公共子序列长度
     */
    static int lcs1(int[] nums1, int i, int[] nums2, int j) {
        if (i == 0 || j == 0) return 0;
        if (nums1[i - 1] == nums2[j - 1]) {
            return lcs1(nums1, i - 1, nums2, j - 1) + 1;
        } else {
            return Math.max(lcs1(nums1, i - 1, nums2, j),
                    lcs1(nums1, i, nums2, j - 1));
        }
    }

    public static void main(String[] args) {
        System.out.println(lcs(new int[] {1, 3, 5, 9, 10}, new int[] {1, 4, 9, 10}));
    }
}
