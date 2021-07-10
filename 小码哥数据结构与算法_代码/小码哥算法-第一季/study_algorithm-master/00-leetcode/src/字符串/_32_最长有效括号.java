package 字符串;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/longest-valid-parentheses/
 */
public class _32_最长有效括号 {
    // 栈
    // https://leetcode-cn.com/problems/longest-valid-parentheses/solution/shou-hua-tu-jie-zhan-de-xiang-xi-si-lu-by-hyj8/
    public int longestValidParentheses2(String s) {
        char[] chars = s.toCharArray();
        if (chars.length == 0) return 0;
        // 栈中元素为左括号索引，以及「最后一个没有被匹配的右括号的下标」
        Stack<Integer> stack = new Stack<>();
        int maxLen = 0;
        // 为了保持统一，我们在一开始的时候往栈中放入一个值为-1的元素，当作最后一个没有被匹配的右括号的下标
        stack.push(-1);

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                stack.push(i);
            } else { // ')'
                // 遇到右括号，出栈
                stack.pop();
                // 若此时栈为空，则放入右括号作为参照
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    // 若栈不为空，计算长度
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }

        return maxLen;
    }

    // DP
    public int longestValidParentheses(String s) {
        char[] chars = s.toCharArray();
        if (chars.length == 0) return 0;
        // dp[i]:以chars[i]字符结尾的，最长有效括号长度
        int[] dp = new int[chars.length];
        int maxLen = 0;

        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == ')') {
                if (chars[i - 1] == '(') {
                    // 因为要访问i - 2的索引，所以需要注意边界值
                    dp[i] = i > 2 ? dp[i - 2] + 2 : 2;
                } else if (i - dp[i - 1] - 1 >= 0 && chars[i - dp[i - 1] - 1] == '('){
                    // 若前一个字符为')'，则需要去看下，前一个字符的最大有效括号开始位置的前一个位置，是否为'('
                    // 为'('的话，刚好可以和i位置的')'构成一组括号
                    // 前一个位置的索引为((i - 1) - dp[i - 1] + 1) - 1 = i - dp[i - 1] - 1
                    // 并且要加上再前一个位置的dp值
                    dp[i] = dp[i - 1] + 2 + ((i - dp[i - 1] - 2) >= 0 ? dp[i - dp[i - 1] - 2] : 0);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        String s = ")()())";
        _32_最长有效括号 test = new _32_最长有效括号();
        System.out.println(test.longestValidParentheses(s));
    }
}
