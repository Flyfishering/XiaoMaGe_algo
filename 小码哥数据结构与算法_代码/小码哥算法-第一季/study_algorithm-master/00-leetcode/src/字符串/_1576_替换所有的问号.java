package 字符串;

public class _1576_替换所有的问号 {
    public String modifyString(String s) {
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == '?') {
                char left = i > 0 ? cs[i - 1] : '?';
                char right = i < cs.length - 1 ? cs[i + 1] : '?';
                char c = 'a';
                while (c == left || c == right) c++;
                cs[i] = c;
            }
        }
        return new String(cs);
    }
}
