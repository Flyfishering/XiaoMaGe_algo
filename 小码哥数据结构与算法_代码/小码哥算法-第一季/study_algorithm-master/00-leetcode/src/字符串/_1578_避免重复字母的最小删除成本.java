package 字符串;

/**
 * https://leetcode-cn.com/problems/minimum-deletion-cost-to-avoid-repeating-letters/
 * 贪心
 */
public class _1578_避免重复字母的最小删除成本 {
    // 求出相同字母里面cost最大的留下来。
    public int minCost(String s, int[] cost) {
        char[] cs = s.toCharArray();
        int ans = 0, li = 0;

        while(li < cs.length) {
            int sum = 0;
            int maxValue = 0;
            char c = cs[li];
            while (li < cs.length && cs[li] == c) {
                sum += cost[li];
                maxValue = Math.max(maxValue, cost[li]);
                li++;
            }
            ans += sum - maxValue;
        }
        return ans;
    }

    // 求最小的，删掉，如果删掉的是当前元素，则做一个交换
    public int minCost2(String s, int[] cost) {
        char[] cs = s.toCharArray();
        int ans = 0;

        for (int i = 1; i < cs.length; i++) {
            if (cs[i] == cs[i - 1]) {
                if (cost[i] < cost[i - 1]) {
                    ans += cost[i];
                    cost[i] = cost[i - 1];
                } else {
                    ans += cost[i - 1];
                }
            }
        }
        return ans;
    }

    // 跟上一个解法是一个意思
    public int minCost3(String s, int[] cost) {
        char[] cs = s.toCharArray();
        int ans = 0;

        for (int i = 1; i < cs.length; i++) {
            if (cs[i] == cs[i - 1]) {
                ans += Math.min(cost[i], cost[i - 1]);
                // 删的是最小的cost，留下来的是最大的，下次比较还要用
                cost[i] = Math.max(cost[i], cost[i - 1]);
            }
        }
        return ans;
    }
}
