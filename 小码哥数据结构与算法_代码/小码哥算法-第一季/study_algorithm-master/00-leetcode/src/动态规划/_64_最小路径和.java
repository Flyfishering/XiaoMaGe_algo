package 动态规划;

/**
 * https://leetcode-cn.com/problems/minimum-path-sum/
 */
public class _64_最小路径和 {
    // 优化成一维数组
    public int minPathSum(int[][] grid) {
        int rowLen = grid.length;
        int colLen = grid[0].length;
        int[] dp = new int[colLen];

        dp[0] = grid[0][0];

        for (int col = 1; col < colLen; col++) {
            dp[col] = dp[col - 1] + grid[0][col];
        }

        for (int row = 1; row < rowLen; row++) {
            dp[0] = dp[0] + grid[row][0];
            for (int col = 1; col < colLen; col++) {
                dp[col] = Math.min(dp[col - 1], dp[col])
                        + grid[row][col];
            }
        }
        return dp[colLen - 1];
    }
    // dp
    public int minPathSum2(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];

        dp[0][0] = grid[0][0];

        for (int row = 1; row < grid.length; row++) {
            dp[row][0] = dp[row - 1][0] + grid[row][0];
        }

        for (int col = 1; col < grid[0].length; col++) {
            dp[0][col] = dp[0][col - 1] + grid[0][col];
        }

        for (int row = 1; row < grid.length; row++) {
            for (int col = 1; col < grid[0].length; col++) {
                dp[row][col] = Math.min(dp[row][col - 1], dp[row - 1][col])
                        + grid[row][col];
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }
}
