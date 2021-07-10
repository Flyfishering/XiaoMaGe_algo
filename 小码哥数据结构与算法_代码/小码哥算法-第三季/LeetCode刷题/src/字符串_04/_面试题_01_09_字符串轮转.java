package 字符串_04;

/*
* https://leetcode-cn.com/problems/string-rotation-lcci/
* */
public class _面试题_01_09_字符串轮转 {
    public boolean isFlipedString(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        if (s1.length() != s2.length()) return false;
        s1 = s1 + s1;
        return s1.contains(s2);
    }
}
