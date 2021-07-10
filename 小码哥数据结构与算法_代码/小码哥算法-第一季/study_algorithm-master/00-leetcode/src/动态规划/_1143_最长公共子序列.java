package 动态规划;

/**
 * https://leetcode-cn.com/problems/longest-common-subsequence/
 */
public class _1143_最长公共子序列 {
    // dp + 滚动数组(优化至一维数组,并且用较短的字符串的长度作为dp数组的长度)
    public int longestCommonSubsequence(String text1, String text2) {
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();

        char[] cols, rows;
        if (chars1.length > chars2.length) {
            cols = chars2;
            rows = chars1;
        } else {
            cols = chars1;
            rows = chars2;
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
}
