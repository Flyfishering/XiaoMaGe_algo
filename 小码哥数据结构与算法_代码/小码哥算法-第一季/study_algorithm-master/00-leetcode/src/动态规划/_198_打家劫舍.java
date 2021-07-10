package 动态规划;

/**
 * https://leetcode-cn.com/problems/house-robber/
 */
public class _198_打家劫舍 {
    // DP
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        // dp[i]表示前i+1家可以偷时的最大收益
        int[] dp = new int[nums.length];
        // dp[0] = nums[0], dp[1] = max(dp[0], dp[1])
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        // dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1])
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[nums.length - 1];
    }
    // 优化空间
    public int rob2(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int first, second;

        first = nums[0];
        second = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            int tmp = second;
            second = Math.max(first + nums[i], second);
            first = tmp;
        }
        return second;
    }
}
