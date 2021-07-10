package 字符串_04;

import java.util.HashMap;

/*
* https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
* */
public class _3_无重复字符的最长子串 {
    public int lengthOfLongestSubstring(String s) {
        if (s == null) return 0;

        char[] chars = s.toCharArray();
        if (chars.length == 0) return 0;

        HashMap<Character,Integer> prevIndex = new HashMap<>();
        prevIndex.put(chars[0],0);

        int li = 0; // 以i - 1位置字符结尾的最长不重复字符串的开始索引（最左索引）
        int max = 1;

        for (int i = 1; i < s.length(); i++) {
            // i位置字符上一次出现的位置
            Integer pi = prevIndex.get(chars[i]);
            if (pi !=null && li <= pi) { // 只有和我一样的字符(pi)出现在我前一个字符最长串(li)的右边,才需要更新li
                li = pi + 1;  // 更新li
            }
            prevIndex.put(chars[i],i);  // 存储这个字符出现的位置
            max = Math.max(max,i - li + 1); // 求出最长不重复子串的长度
        }

        return max;
    }
}
