package 动态规划;

/**
 * https://leetcode-cn.com/problems/edit-distance/
 */
public class _72_编辑距离 {
    public int minDistance(String word1, String word2) {
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();

        // dp[i][j]:word1[0, i)转化为word2[0, j)的最小操作数
        int[][] dp = new int[chars1.length + 1][chars2.length + 1];

        // 初始化dp第0列
        for (int row = 1; row <= chars1.length; row++) {
            dp[row][0] = row;
        }

        // 初始化dp第0行
        for (int col = 1; col <= chars2.length; col++) {
            dp[0][col] = col;
        }

        for (int row = 1; row <= chars1.length; row++) {
            for (int col = 1; col <= chars2.length; col++) {
                int top = dp[row - 1][col] + 1;
                int left = dp[row][col - 1] + 1;
                int leftTop = chars1[row - 1] == chars2[col - 1] ?
                        dp[row - 1][col - 1] : dp[row - 1][col - 1] + 1;
                dp[row][col] = Math.min(Math.min(top, left), leftTop);
            }
        }
        return dp[chars1.length][chars2.length];
    }
}
