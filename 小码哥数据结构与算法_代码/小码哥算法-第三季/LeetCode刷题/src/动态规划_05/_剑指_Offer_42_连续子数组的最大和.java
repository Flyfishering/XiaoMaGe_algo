package 动态规划_05;

/*
* https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/
* */
public class _剑指_Offer_42_连续子数组的最大和 {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int dp = nums[0];
        int max = dp;
        for (int i = 1; i < nums.length; i++) {
            if (dp <= 0) {
                dp = nums[i];
            } else {
                dp = dp + nums[i];
            }
            max = Math.max(dp,max);
        }
        return max;
    }
}
