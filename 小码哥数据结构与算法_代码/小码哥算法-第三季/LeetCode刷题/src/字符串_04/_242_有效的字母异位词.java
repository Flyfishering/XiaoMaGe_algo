package 字符串_04;

/*
* https://leetcode-cn.com/problems/valid-anagram/
* */
public class _242_有效的字母异位词 {
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null) return false;
        if (s.length() != t.length()) return false;
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();

        int[] counts = new int[26];
        for (int i = 0; i < sArray.length; i++) {
            counts[sArray[i] - 'a']++;
        }
        for (int i = 0; i < tArray.length; i++) {
            if (--counts[tArray[i] - 'a'] < 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        _242_有效的字母异位词 o = new _242_有效的字母异位词();
        o.isAnagram("anagram","nagaram");
    }
}
