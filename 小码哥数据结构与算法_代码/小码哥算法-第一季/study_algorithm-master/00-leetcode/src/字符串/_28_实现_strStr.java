package 字符串;

/**
 * https://leetcode-cn.com/problems/implement-strstr/
 */
public class _28_实现_strStr {
    public int strStr(String haystack, String needle) {
        char[] cs1 = haystack.toCharArray();
        char[] cs2 = needle.toCharArray();
        if (cs2.length == 0) return 0;

        int p_index = 0;
        for (int i = 0; i < cs1.length - cs2.length + 1; i++) {
            if (cs1[i] == cs2[p_index]) {
                int s_index = i;
                while (p_index < cs2.length && cs1[s_index] == cs2[p_index]) {
                    s_index++;
                    p_index++;
                }
                if (p_index == cs2.length) {
                    return i;
                } else {
                    p_index = 0;
                }
            }
        }
        return -1;
    }
}
