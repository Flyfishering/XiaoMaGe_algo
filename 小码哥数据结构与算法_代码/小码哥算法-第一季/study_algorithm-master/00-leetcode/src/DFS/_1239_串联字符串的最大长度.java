package DFS;

import java.util.List;

/**
 * https://leetcode-cn.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/
 */
public class _1239_串联字符串的最大长度 {
    int maxLen;
    public int maxLength(List<String> arr) {
        dfs(arr, 0, 0, 0);
        return maxLen;
    }

    /**
     *
     * @param bitmask 位掩码，用来判断字符串是否有重复字符
     * @param idx 层数
     * @param len 上一层的串联字符串长度
     */
    private void dfs(List<String> arr, int bitmask, int idx, int len) {
        if (idx == arr.size()) {
            maxLen = Math.max(maxLen, len);
            return;
        }
        String str = arr.get(idx);
        int newBit = isUnique(bitmask, str);
        if (newBit != -1) dfs(arr, newBit, idx + 1, len + str.length());
        dfs(arr, bitmask, idx + 1, len);
    }

    private int isUnique(int bitmask, String str) {
        for (char c : str.toCharArray()) {
            // 注意这边是不等于0!
            if ((bitmask & (1 << (c - 'a'))) != 0) {
                return -1;
            }
            bitmask |= (1 << (c - 'a'));
        }
        return bitmask;
    }

}
