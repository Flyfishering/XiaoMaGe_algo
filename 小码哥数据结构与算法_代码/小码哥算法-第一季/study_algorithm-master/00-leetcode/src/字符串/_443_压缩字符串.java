package 字符串;

/**
 * https://leetcode-cn.com/problems/string-compression/
 */
public class _443_压缩字符串 {
    public int compress(char[] chars) {
        int cur = 0;
        int i = 0;
        while (i < chars.length) {
            int ri = i + 1;
            int cnt = 1;
            while (ri < chars.length && chars[i] == chars[ri]) {
                cnt++;
                ri++;
            }
            chars[cur++] = chars[i];
            if (cnt != 1) {
                String sc = String.valueOf(cnt);
                for (char c : sc.toCharArray()) {
                    chars[cur++] = c;
                }
            }
            i = ri;
        }
        return cur;
    }
}
