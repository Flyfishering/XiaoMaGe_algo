package 动态规划;

/**
 * https://leetcode-cn.com/problems/house-robber-ii/
 */
public class _213_打家劫舍_II {
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);

        return Math.max(myRob(nums, 0, nums.length - 2), myRob(nums, 1, nums.length - 1));
    }

    private int myRob(int[] nums, int start, int end) {
            int first = nums[start], second = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            int temp = second;
            second = Math.max(second, first + nums[i]);
            first = temp;
        }
        return second;
    }
}
