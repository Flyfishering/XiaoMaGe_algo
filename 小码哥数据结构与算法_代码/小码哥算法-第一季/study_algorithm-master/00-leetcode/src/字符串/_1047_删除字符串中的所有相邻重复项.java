package 字符串;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string/
 */
public class _1047_删除字符串中的所有相邻重复项 {

    public String removeDuplicates(String S) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < S.length() ; i++) {
            int len = sb.length();
            if (len != 0 && S.charAt(i) == sb.charAt(len - 1)) {
                sb.deleteCharAt(len - 1);
            } else {
                sb.append(S.charAt(i));
            }
        }

        return sb.toString();
    }

    public String removeDuplicates2(String S) {
        char[] sChars = S.toCharArray();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < sChars.length; i++) {
            if (!stack.isEmpty() && stack.peek().equals(sChars[i])) {
                stack.pop();
            } else {
                stack.push(sChars[i]);
            }
        }
        int len = stack.size();
        char[] res = new char[len];
        for (int i = len - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }

        return new String(res);
    }
}
