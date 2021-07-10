package 字符串;

/**
 * https://leetcode-cn.com/problems/valid-palindrome/
 */
public class _125_验证回文串 {
    public boolean isPalindrome(String s) {
        if (s == null) return false;
        char[] cs = s.toCharArray();
        if (cs.length == 0) return true;

        int li = 0, ri = cs.length - 1;

        // 不能有等于号。会越界
        while (li < ri) {
            while (li < ri && !Character.isLetterOrDigit(cs[li])) {
                li++;
            }
            while (li < ri && !Character.isLetterOrDigit(cs[ri])) {
                ri--;
            }

            if (Character.toLowerCase(cs[li]) != Character.toLowerCase(cs[ri])) return false;
            li++;
            ri--;
        }
        return true;
    }
}
