package 动态规划;

/**
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 */
public class _5_最长回文子串 {
    // DP(按照课件第一种解法的思路自己尝试)
    public String longestPalindrome3(String s) {
        char[] cs = s.toCharArray();
        // dp[i][j]:s[i, j]若为回文串，则记录长度。若不是回文串，则为0
        int[][] dp = new int[cs.length][cs.length];
        // 最长回文子串的开始索引
        int begin = 0;
        // 最长回文子串的长度
        int maxLen = 1;

        // 从下到上(i由大到小)
        for (int i = cs.length - 1; i >= 0; i--) {
            // 从左到右(j由小到大)
            for (int j = i; j < cs.length; j++) {
//                if (cs[i] == cs[j] && ((j - i + 1 <= 2) || (dp[i + 1][j - 1] != 0))) {
//                    // 回文子串的条件可以归纳为这个条件
//                }
                // [i, j]长度小于等于2
                if (j - i + 1 <= 2) {
                    if (cs[i] == cs[j]) {
                        dp[i][j] = j - i + 1;
                    }
                } else { // [i, j]长度大于2
                    // 若[i, j]除开i,j处的字符外，仍是回文串，且i,j处的字符相对，则[i, j]也是回文串
                    if (cs[i] == cs[j] && dp[i + 1][j - 1] != 0) {
                        dp[i][j] = j - i + 1;
                    }
                }
                if (dp[i][j] > maxLen) {
                    begin = i;
                    maxLen = dp[i][j];
                }
            }
        }
        return new String(cs, begin, maxLen);
    }

    // DP，课件的第一个解法，时间，空间均为O(n^2)
    // 可以优化，把二维数组优化成一维
    public String longestPalindromeDp2(String s) {
        if (s == null) return null;
        char[] cs = s.toCharArray();
        if (cs.length <= 1) return s;
        // 最长回文子串的长度（至少是1）
        int maxLen = 1;
        // 最长回文子串的开始索引
        int begin = 0;
        boolean[][] dp = new boolean[cs.length][cs.length];
        // 从下到上（i由大到小）
        for (int i = cs.length - 1; i >= 0; i--) {
            // 从左到右（j由小到大）
            for (int j = i; j < cs.length; j++) {
                // cs[i, j]的长度
                int len = j - i + 1;
                dp[i][j] = (cs[i] == cs[j]) && (len <= 2 || dp[i + 1][j - 1]);
                if (dp[i][j] && len > maxLen) { // 说明cs[i, j]是回文子串
                    maxLen = len;
                    begin = i;
                }
            }
        }
        return new String(cs, begin, maxLen);
    }

    // DP，课件的第一个解法，二维数组优化成一维
    public String longestPalindromeDp(String s) {
        if (s == null) return null;
        char[] cs = s.toCharArray();
        if (cs.length <= 1) return s;
        // 最长回文子串的长度（至少是1）
        int maxLen = 1;
        // 最长回文子串的开始索引
        int begin = 0;
        boolean[] dp = new boolean[cs.length];

        // 从下到上（i由大到小）
        for (int i = cs.length - 1; i >= 0; i--) {
            boolean cur = false;
            // 从左到右（j由小到大）
            for (int j = i; j < cs.length; j++) {
                boolean leftDown = cur;
                cur = dp[j];
                // cs[i, j]的长度
                int len = j - i + 1;
                dp[j] = (cs[i] == cs[j]) && (len <= 2 || leftDown);
                if (dp[j] && len > maxLen) { // 说明cs[i, j]是回文子串
                    maxLen = len;
                    begin = i;
                }
            }
        }
        return new String(cs, begin, maxLen);
    }

    // 扩展中心
    public String longestPalindromeEx(String s) {
        char[] cs = s.toCharArray();
        if (cs.length < 2) return s;
        // 最长回文子串的长度（至少是1）
        int maxLen = 1;
        // 最长回文子串的开始索引
        int begin = 0;

        for (int i = cs.length - 2; i >= 1; i--) {
            // 以i位置的字符为中心向左右扫描得到的最长回文子串长度
            int len1 = palindromeLength(cs, i - 1, i + 1);
            // 以i位置字符右侧的间隙为中心向左右扫描得到的最长回文子串长度
            int len2 = palindromeLength(cs, i, i + 1);
            len1 = Math.max(len1, len2);
            if (len1 > maxLen) {
                maxLen = len1;
                begin = i - ((len1 - 1) >> 1);
            }
        }

        // 处理以0号字符右边间隙为扩展中心的情况
        if (cs[0] == cs[1] && maxLen < 2) {
            maxLen = 2;
            begin = 0;
        }

        return new String(cs, begin, maxLen);
    }

    // 返回值是，从l开始向左扫描，从r开始向右扫描，获得的最长回文子串的长度
    private int palindromeLength(char[] cs, int l, int r) {
        while (l >= 0 && r < cs.length && cs[l] == cs[r]) {
            l--;
            r++;
        }
        // r - l + 1 - 2
        return r - l - 1;
    }

    // 扩展中心法的优化，基于连续的相同的子串作为扩展中心
    public String longestPalindromeEx2(String s) {
        char[] cs = s.toCharArray();
        if (cs.length < 2) return s;
        // 最长回文子串的长度（至少是1）
        int maxLen = 1;
        // 最长回文子串的开始索引
        int begin = 0;
        int i = 0;

        while (i < cs.length - 1) {
            int l = i - 1;
            // 找到右边第一个跟cs[i]不相等的位置
            int r = i;
            while (++r < cs.length && cs[i] == cs[r]);
            // r会成为新的i
            i = r;

            // 向左向右扩展
            while (l >= 0 && r < cs.length && cs[l] == cs[r]) {
                l--;
                r++;
            }
            int len = r - l - 1;
            if (maxLen < len) {
                maxLen = len;
                begin = l + 1;
            }
        }

        return new String(cs, begin, maxLen);
    }

}
