package DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 */
public class _17_电话号码的字母组合2 {
    private char[][] lettersArray = {
            {'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'},
            {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}
    };

    public List<String> letterCombinations(String digits) {
        if (digits == null) return null;
        char[] chars = digits.toCharArray();
        List<String> list = new ArrayList<>();
        if (chars.length == 0) return list;
        char[] string = new char[chars.length];

        dfs(0, chars, list, string);

        return list;
    }

    /**
     * @param idx 正在搜索的第idx层
     */
    private void dfs(int idx, char[] chars, List<String> list, char[] string) {
        if (idx == chars.length) {
            // 已经进入到最后一层了，不能再往下搜
            // 得到了一个正确的解
            list.add(new String(string));
            return;
        }

        // 先枚举这一层的所有可能
        char digit = chars[idx];
        char[] letters = lettersArray[digit - '2'];
        for (char letter : letters) {
            string[idx] = letter;
            dfs(idx + 1, chars, list, string);
        }
    }

}
