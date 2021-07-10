package 动态规划;

/**
 * https://leetcode-cn.com/problems/wildcard-matching/
 */
public class _44_通配符匹配 {
    // DP。解题思路参考第十题。这题比第十题简单。
    public boolean isMatch(String s, String p) {
        char[] cs = s.toCharArray();
        char[] cp = p.toCharArray();

        boolean[][] dp = new boolean[cs.length + 1][cp.length + 1];

        // 初始化
        dp[0][0] = true;
        for (int j = 1; j <= cp.length; j++) {
            if (cp[j - 1] == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }

        // 填格子
        for (int i = 1; i <= cs.length; i++) {
            for (int j = 1; j <= cp.length; j++) {
                if (cs[i - 1] == cp[j - 1] || cp[j - 1] == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (cp[j - 1] == '*') {
                    dp[i][j] = dp[i][j - 1]
                            || dp[i - 1][j];
                }
            }
        }

        return dp[cs.length][cp.length];
    }
}
