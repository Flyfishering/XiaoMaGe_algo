package DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/generate-parentheses/
 */
public class _22_括号生成 {
    // 当左括号，右括号的数量一样时，只能选左括号
    /*
    什么情况可以选左括号？
    (左括号的数量 > 0)

    什么情况可以选右括号?
    (右括号的数量 > 0 并且 左括号的数量 != 右括号的数量)
     */
    public List<String> generateParenthesis(int n) {
        char[] string = new char[n << 1];
        List<String> list = new ArrayList<>();

        dfs(0, n, n, string, list);
        return list;
    }

    private void dfs(int idx, int leftRemain, int rightRemain, char[] string, List<String> list) {
        if (idx == string.length) {
            list.add(new String(string));
            return;
        }

        // 枚举这一层的所有可能
        // 选择一种可能，进入下一层搜索

        // 选择左括号
        if (leftRemain > 0) {
            string[idx] = '(';
            dfs(idx + 1, leftRemain - 1, rightRemain, string, list);
        }

        // 选择右括号
        if (rightRemain > 0 && leftRemain != rightRemain) {
            string[idx] = ')';
            dfs(idx + 1, leftRemain, rightRemain - 1, string, list);
        }
    }
}
