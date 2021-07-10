package 字符串_04;

/*
* https://leetcode-cn.com/problems/reverse-words-in-a-string/
* */
public class _151_翻转字符串里的单词 {
    public String reverseWords(String s) {
        if (s == null) return "";
        char[] chars = s.toCharArray();

        int len = 0;           // 字符串最终的有效长度
        int cur = 0;           // 当前用来存放字符的位置
        boolean space = true;  // 用于标记前一个字符是不是空格

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') { // 如果遍历到的不是空格
                chars[cur++] = chars[i];
                space = false;
            } else if (!space){
                chars[cur++] = ' ';
                space = true;
            }
        }
        len = space ? (cur - 1) : cur;
        if (len <= 0) return "";

        // 先对整个字符串进行完全的反转
        reverse(chars,0,len);

        // 对每一个单词进行逆序
        // 前一个空格字符的位置（有-1位置有个假想的哨兵，就是一个假想的空格字符）
        int prevSapceIdx = -1;
        for (int i = 0; i < len; i++) {
            if (chars[i] != ' ') continue;
            reverse(chars,prevSapceIdx+1,i);
            prevSapceIdx = i;
        }
        // 翻转最后一个单词
        reverse(chars,prevSapceIdx+1,len);

        return new String(chars,0,len);
    }

    private static void  reverse(char[] chars,int li,int ri) {
        ri--;
        while (li < ri) {
            char tmp = chars[li];
            chars[li] = chars[ri];
            chars[ri] = tmp;
            li++;
            ri--;
        }
    }
}
