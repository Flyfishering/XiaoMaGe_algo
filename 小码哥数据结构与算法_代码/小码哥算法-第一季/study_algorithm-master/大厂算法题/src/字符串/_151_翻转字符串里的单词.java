package 字符串;

/**
 * https://leetcode-cn.com/problems/reverse-words-in-a-string/
 */
public class _151_翻转字符串里的单词 {
    public String reverseWords(String s) {
        if (s == null) return "";
        char[] chars = s.toCharArray();
        // 消除多余的空格
        // 字符串的有效长度
        int len = 0;
        // 当前可以存放字符的位置
        int cur = 0;
        // 前一个字符是否为空格字符。第一个空格会算作有效字符(在-1位置有个假想的哨兵，就是一个假想的空格字符)
        boolean space = true;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') { // 非空字符
                chars[cur++] = chars[i];
                space = false;
            } else if (!space) { // 空格字符。当前一个字符不为空格字符的时候，这个空格当作有效空格
                chars[cur++] = ' ';
                space = true;
            }
        }
        // 若最后扫描的字符是空格，则字符串的有效长度是cur - 1。否则刚好是cur
        len = space ? (cur - 1) : cur;
        if (len <= 0) return "";

        // 对整个字符串进行逆序操作
        reverse(chars, 0, len);
        // 前一个空格字符的位置(在-1位置有个假想的哨兵，就是一个假想的空格字符)
        int prevSpaceIdx = -1;
        for (int i = 0; i < len; i++) {
            if (chars[i] != ' ') continue;
            reverse(chars, prevSpaceIdx + 1, i);
            prevSpaceIdx = i;
        }
        reverse(chars, prevSpaceIdx + 1, len);

        return new String(chars, 0, len);
    }

    // 将[l, r)范围内的字符串进行逆序
    private void reverse(char[] chars, int li, int ri) {
        ri--;
        while (li < ri) {
            char temp = chars[li];
            chars[li] = chars[ri];
            chars[ri] = temp;
            li++;
            ri--;
        }
    }
}
