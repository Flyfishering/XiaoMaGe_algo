package 动态规划;

/**
 * https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/
 */
public class _剑指_Offer_46_把数字翻译成字符串 {
    public int translateNum(int num) {
        String str = String.valueOf(num);
        char[] cs = str.toCharArray();
        int[] dp = new int[cs.length + 1];

        dp[0] = 1;
        for (int i = 1; i <= cs.length; i++) {
            dp[i] = dp[i - 1];
            int b = i > 1 ? (cs[i - 2] - '0') * 10 + cs[i - 1] - '0' : cs[i - 1] - '0';
            if (b >= 10 && b <= 25) dp[i] += dp[i - 2];
        }
        return dp[cs.length];
    }
}
