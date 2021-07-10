package 动态规划;

/**
 * https://leetcode-cn.com/problems/regular-expression-matching/
 */
public class _10_正则表达式匹配 {
    // DP
    // https://leetcode-cn.com/problems/regular-expression-matching/solution/shou-hui-tu-jie-wo-tai-nan-liao-by-hyj8/
    public boolean isMatch(String s, String p) {
        char[] cs = s.toCharArray();
        char[] cp = p.toCharArray();

        // dp[i][j]:表示s的前i个字符，p的前j个字符是否能够匹配
        boolean[][] dp = new boolean[cs.length + 1][cp.length + 1];

        // 初期值
        // s为空，p为空，能匹配上
        dp[0][0] = true;
        // p为空，s不为空，必为false(boolean数组默认值为false，无需处理)

        // s为空，p不为空，由于*可以匹配0个字符，所以有可能为true
        for (int j = 1; j <= cp.length; j++) {
            if (cp[j - 1] == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        // 填格子
        for (int i = 1; i <= cs.length; i++) {
            for (int j = 1; j <= cp.length; j++) {
                // 文本串和模式串末位字符能匹配上
                if (cs[i - 1] == cp[j - 1] || cp[j - 1] == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (cp[j - 1] == '*') { // 模式串末位是*
                    // 模式串*的前一个字符能够跟文本串的末位匹配上
                    if (cs[i - 1] == cp[j - 2] || cp[j - 2] == '.') {
//                        dp[i][j] = dp[i][j - 2]      // *匹配0次的情况
//                                || dp[i - 1][j - 2]  // *匹配1次的情况
//                                || dp[i - 1][j];     // *匹配多次的情况
                        dp[i][j] = dp[i][j - 2]      // *匹配0次的情况
                                || dp[i - 1][j];     // *匹配1次或多次的情况(1次和多次可以合并)
                    } else { // 模式串*的前一个字符不能够跟文本串的末位匹配
                        dp[i][j] = dp[i][j - 2];     // *只能匹配0次
                    }
                }
            }
        }
        return dp[cs.length][cp.length];
    }

    // 暴力递归(可以用记忆化优化，这边没有做优化)
    // https://leetcode-cn.com/problems/regular-expression-matching/solution/liang-chong-shi-xian-xiang-xi-tu-jie-10-48bgj/
    public boolean isMatch2(String s, String p) {
        return dfs(s, p);
    }

    private boolean dfs(String s, String p) {
        // 一方长度为0的时候，返回另外一方长度是否也为0
        if (p.isEmpty()) {
            return s.isEmpty();
        }

        // 首字符是否匹配
        boolean isFirstMatch = s.length() > 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
        // 第二个字符是否是*
        boolean isMatchAny = p.length() > 1 && p.charAt(1) == '*';

        if (isMatchAny) {
            return (isFirstMatch && dfs(s.substring(1), p))
                    || dfs(s, p.substring(2));
        } else {
            return isFirstMatch && dfs(s.substring(1), p.substring(1));
        }
    }

}
