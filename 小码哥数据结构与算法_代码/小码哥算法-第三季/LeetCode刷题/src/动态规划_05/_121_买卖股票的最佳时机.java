package 动态规划_05;

/*
* https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
* */
public class _121_买卖股票的最佳时机 {
    /*
    * 解法1: 找打最小价格,每发现一个比他大的,就求利润,最后取最大利润
    * */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int maxProfit = 0;        // 前面扫描过的最大利润
        int minPrice = prices[0]; // 前面扫描过的最小价格
        for (int i = 1; i < prices.length; i++) {
            if (minPrice > prices[i]) {
                minPrice = prices[i];
            } else {
                maxProfit = Math.max(maxProfit,prices[i] - minPrice);
            }
        }
        return maxProfit;
    }

    /*
    * 解法2:动态规划的思想,求出相邻天的利润,然后就转变成了最长连续子序列和的问题
    * */
    public int maxProfit1(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        if (prices.length == 1) return 1;

        int[] res = new int[prices.length-1];
        for (int i = 0; i < prices.length -1; i++) {
            res[i] = prices[i+1] - prices[i];
        }

        int dp = res[0];
        int max = dp;
        for (int i = 1; i < res.length; i++) {
            if (dp <= 0) {
                dp = res[i];
            } else {
                dp = res[i] + dp;
            }
            max = Math.max(dp,max);
        }
        return max > 0 ? max : 0;
    }

    public static void main(String[] args) {
        _121_买卖股票的最佳时机 o = new _121_买卖股票的最佳时机();
        o.maxProfit1(new int[]{1});
    }


}
