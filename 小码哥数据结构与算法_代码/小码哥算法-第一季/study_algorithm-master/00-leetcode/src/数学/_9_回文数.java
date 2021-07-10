package 数学;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/palindrome-number/
 */
public class _9_回文数 {
    // 把这个数翻转，看是否相等
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int num = x;
        int cur = 0;
        while (num != 0) {
            cur = cur * 10 + num % 10;
            num /= 10;
        }
        return x == cur;
    }

    // 优化的解法，只比较数的一半
    public boolean isPalindrome2(int x) {
        if (x < 0 || (x % 10 == 0 && x / 10 != 0)) return false;
        int num = 0;
        while (x > num) {
            num = num * 10 + x % 10;
            x /= 10;
        }
        // x == num,如，1221
        // x == num / 10,如121
        return x == num || x == (num / 10);
    }
}
