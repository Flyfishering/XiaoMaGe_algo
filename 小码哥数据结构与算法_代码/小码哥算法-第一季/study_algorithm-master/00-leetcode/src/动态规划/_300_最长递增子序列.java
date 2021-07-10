package 动态规划;

/**
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/
 */
public class _300_最长递增子序列 {

    // 模拟扑克牌堆 + 二分优化
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int[] top = new int[nums.length]; // 牌堆数组
        int len = 0; // 牌堆数量

        for (int num : nums) {
            int begin = 0;
            int end = len;
            while (begin < end) {
                int mid = (begin + end) >> 1;
                if (num <= top[mid]) { // 找第一个大于等于该牌的牌顶。若大于等于，则去左边找。这个地方跟之前的二分搜索不同。
                    end = mid;
                } else {
                    begin = mid + 1;
                }
            }
            top[begin] = num;
            if (begin == len) {
                len++;
            }
        }
        return len;
    }

    // 模拟扑克牌堆
    public int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int[] top = new int[nums.length]; // 牌堆数组
        int len = 0; // 牌堆数量

        for (int num : nums) {
            int j = 0;
            for (; j < len; j++) {
                if (top[j] >= num) {
                    top[j] = num;
                    break;
                }
            }
            if (j == len) {
                top[j] = num;
                len++;
            }
        }
        return len;
    }

    // dp
    public int lengthOfLIS1(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int[] dp = new int[nums.length];
        int max = dp[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }
}
