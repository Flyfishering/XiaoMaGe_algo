package com.mj;

import java.util.Locale;

// longest common substring,dp思路跟LIS很像(以...结尾的最大子...)
public class LCSubstring {

    // dp + 一维数组优化(考虑长度短的串做列,利用从右往左算来更简化代码)
    static int lsc(String str1, String str2) {
        if (str1 == null || str2 == null) return 0;
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        if (chars1.length == 0 || chars2.length == 0) return 0;
        int[] dp = new int[chars2.length + 1];
        int max = 0;
        char[] rows = chars1, cols = chars2;
        if (rows.length < cols.length) {
            rows = chars2;
            cols = chars1;
        }

        for (int row = 1; row <= rows.length; row++) {
            for (int col = cols.length; col >= 1; col--) {
                if (rows[row - 1] == cols[col - 1]) {
                    dp[col] = dp[col - 1] + 1;
                    max = Math.max(max, dp[col]);
                } else {
                    dp[col] = 0; // 滚动一维数组，需要注意不相等的时候，要覆盖原来的值
                }
            }
        }
        return max;
    }

    // dp + 一维数组优化(考虑长度短的串做列)
    static int lsc2(String str1, String str2) {
        if (str1 == null || str2 == null) return 0;
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        if (chars1.length == 0 || chars2.length == 0) return 0;
        int[] dp = new int[chars2.length + 1];
        int max = 0;
        char[] rows = chars1, cols = chars2;
        if (rows.length < cols.length) {
            rows = chars2;
            cols = chars1;
        }

        for (int row = 1; row <= rows.length; row++) {
            int cur = 0;
            for (int col = 1; col <= cols.length; col++) {
                int leftTop = cur; // 注意保留左上角的值
                cur = dp[col];
                if (rows[row - 1] == cols[col - 1]) {
                    dp[col] = leftTop + 1;
                    max = Math.max(max, dp[col]);
                } else {
                    dp[col] = 0; // 滚动一维数组，需要注意不相等的时候，要覆盖原来的值
                }
            }
        }
        return max;
    }

    // dp
    static int lsc1(String str1, String str2) {
        if (str1 == null || str2 == null) return 0;
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        if (chars1.length == 0 || chars2.length == 0) return 0;
        int[][] dp = new int[chars1.length + 1][chars2.length + 1];
        int max = 0;

        for (int i = 1; i <= chars1.length; i++) {
            for (int j = 1; j <= chars2.length; j++) {
                if (chars1[i - 1] == chars2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(lsc2("abcba", "babca"));
    }
}
