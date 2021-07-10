package 动态规划;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/word-break/
 */
public class _139_单词拆分 {
    // https://leetcode-cn.com/problems/word-break/solution/dan-ci-chai-fen-ju-jue-zhuang-xcong-jian-dan-de-xi/
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);

        // dp[i]:s的前i个字符是否能拆分成wordDict里的单词
        boolean[] dp = new boolean[s.length() + 1];
        // 初始值，dp[0] = true;
        dp[0] = true;

        // 状态转移:dp[i] = dp[j] && check(s[j, i))
        // i用来求dp，同时也表示s的前i个字符(长度)
        // j则是用来遍历s[0, i - 1)的索引。
        for (int i = 1; i <= s.length(); i++) {
            for (int j = i - 1; j >= 0 ; j--) {
                if (set.contains(s.substring(j, i)) && dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public boolean wordBreak2(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);

        // dp[i]:s的前i个字符是否能拆分成wordDict里的单词
        boolean[] dp = new boolean[s.length() + 1];
        // 初始值，dp[0] = true;
        dp[0] = true;
        // 状态转移:dp[i] = dp[j] && check(s[j, i))
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i ; j++) {
                if (set.contains(s.substring(j, i)) && dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
