package 动态规划;

public class _121_买卖股票的最佳时机 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int minPrice = prices[0];
        int ans = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > minPrice) {
                ans = Math.max(ans, prices[i] - minPrice);
            } else {
                minPrice = prices[i];
            }
        }

        return ans;
    }
    // DP解法，先求一个存储了相邻两天的价格差的数组
    // 然后对这个数组用DP思想求出最大连续子序列和
    // 这个和就是解。这题用纯dp会比较繁琐
}
