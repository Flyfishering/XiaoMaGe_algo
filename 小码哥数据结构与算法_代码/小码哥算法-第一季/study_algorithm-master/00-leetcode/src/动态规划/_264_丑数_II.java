package 动态规划;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/ugly-number-ii/
 * https://leetcode-cn.com/problems/ugly-number-ii/solution/gong-shui-san-xie-yi-ti-shuang-jie-you-x-3nvs/
 */
public class _264_丑数_II {
    // 最小堆
    public int nthUglyNumber(int n) {
        Queue<Long> queue = new PriorityQueue<>();
        // set用于去除
        Set<Long> set = new HashSet<>();

        int[] nums = {2, 3, 5};

        queue.offer(1L);
        long ans = 1L;

        for (int i = 1; i <= n; i++) {
            ans = queue.remove();
            if (i == n) break;
            for (int num : nums) {
                long ugly = (long) ans * num;
                if (set.add(ugly)) {
                    queue.offer(ugly);
                }
            }
        }

        return (int) ans;
    }

    // 归并(动态规划)
    public int nthUglyNumber2(int n) {
        // dp[i]:第i个丑数
        int[] dp = new int[n + 1];
        // 第一个丑数是1
        dp[1] = 1;
        for (int i2 = 1, i3 = 1, i5 = 1, i = 2; i <= n; i++) {
            int a = dp[i2] * 2, b = dp[i3] * 3, c = dp[i5] * 5;
            dp[i] = Math.min(a, Math.min(b, c));
            if (dp[i] == a) i2++;
            if (dp[i] == b) i3++;
            if (dp[i] == c) i5++;
        }
        return dp[n];
    }
}
