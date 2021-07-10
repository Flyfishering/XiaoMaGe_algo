package 动态规划;

/**
 * https://leetcode-cn.com/problems/decode-ways/
 */
public class _91_解码方法 {
    public int numDecodings(String s) {
        char[] cs = s.toCharArray();
        // dp[i] : s的前i个字符的解码总数
        int[] dp = new int[cs.length + 1];

        // dp[0] = 1
        dp[0] = 1;

        // 1 <= cs[i] <= 9 : dp[i] = dp[i - 1]
        // 0 <= cs[i] <= 6 && 1 <= cs[i - 1] <= 2 : dp[i] = dp[i - 2]
        for (int i = 1; i <= cs.length; i++) {
            if (cs[i - 1] != '0') dp[i] = dp[i - 1];
            int b = i > 1 ? (cs[i - 2] - '0') * 10 + cs[i - 1] - '0' : cs[i - 1] - '0';
            if (b >= 10 && b <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[cs.length];
    }

    // https://leetcode-cn.com/problems/decode-ways/solution/gong-shui-san-xie-gen-ju-shu-ju-fan-wei-ug3dd/
    public int numDecodings2(String s) {
        // 字符串前面加入空格做哨兵,这一步很精妙，可以好好理解下。。。
        s = ' ' + s;
        char[] cs = s.toCharArray();
        int[] dp = new int[cs.length];
        dp[0] = 1;

        for (int i = 1; i < cs.length; i++) {
            int a = cs[i] - '0';
            int b = (cs[i - 1] - '0') * 10 + a;
            if (a >= 1 && a <= 9) dp[i] = dp[i - 1];
            if (b >= 10 && b <= 26) dp[i] += dp[i - 2];
        }
        return dp[cs.length - 1];
    }
}
