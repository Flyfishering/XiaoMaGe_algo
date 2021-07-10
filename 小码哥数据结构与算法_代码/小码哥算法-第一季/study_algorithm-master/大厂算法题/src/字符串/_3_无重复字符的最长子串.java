package 字符串;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 */
public class _3_无重复字符的最长子串 {

    // 滑动窗口3(用map来存储每个字符出现的位置，来快速定位left索引)。算是对滑动窗口1的优化
    public int lengthOfLongestSubstring5(String s) {
        char[] chars = s.toCharArray();
        if (chars.length == 0) return 0;
        HashMap<Character, Integer> map = new HashMap<>();

        int li = 0;
        int maxLen = 0;

        for (int i = 0; i < chars.length; i++) {
            // [li, i]是滑动窗口的有效元素
            // 注意，map中就算包含了chars[i]位置的字符，但是这个字符最后出现的位置可能是在li的左边，也就是说是无效的。
            if (map.containsKey(chars[i])) {
                li = Math.max(li, map.get(chars[i]) + 1);
            }
            map.put(chars[i], i);
            maxLen = Math.max(maxLen, i - li + 1);
        }
        return maxLen;
    }

    // 滑动窗口2(用int数组取代set进行优化)
    public int lengthOfLongestSubstring4(String s) {
        char[] chars = s.toCharArray();
        if (chars.length == 0) return 0;
        // 用来存放每个字符出现的次数
        int[] nums = new int[128];

        int li = 0;
        int maxLen = 0;

        for (int i = 0; i < chars.length; i++) {
            while (nums[chars[i]] != 0) {
                nums[chars[li++]]--;
            }
            nums[chars[i]]++;
            maxLen = Math.max(maxLen, i - li + 1);
        }
        return maxLen;
    }
    // 滑动窗口1
    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        if (chars.length == 0) return 0;
        HashSet<Character> set = new HashSet<>();

        int li = 0;
        int maxLen = 0;

        for (int i = 0; i < chars.length; i++) {
            while (set.contains(chars[i])) {
                set.remove(chars[li++]);
            }
            set.add(chars[i]);
            maxLen = Math.max(maxLen, i - li + 1);
        }
        return maxLen;
    }

    // dp思想，优化了hashmap，换成了数组(假定只有单字节字符)
    public int lengthOfLongestSubstring2(String s) {
        if (s == null) return 0;
        char[] chars = s.toCharArray();
        if (chars.length == 0) return 0;

        // 用来保存每一个字符上一次出现的位置
        int[] prevIdxes = new int[128];
        for (int i = 0; i < 128; i++) {
            prevIdxes[i] = -1;
        }
        prevIdxes[chars[0]] = 0;

        // 以i - 1位置字符结尾的最长不重复字符串的开始索引
        int li = 0;
        int max = 1;
        for (int i = 1; i < chars.length; i++) {
            // i位置字符上一次出现的位置
            int pi = prevIdxes[chars[i]];

            if (li <= pi) {
                li = pi + 1;
            }
            // 存储这个字符出现的位置
            prevIdxes[chars[i]] = i;
            // 求出最长不重复子串的长度
            max = Math.max(max, i - li + 1);
        }
        return max;
    }

    // dp思想
    public int lengthOfLongestSubstring3(String s) {
        if (s == null) return 0;
        char[] chars = s.toCharArray();
        if (chars.length == 0) return 0;

        // 用来保存每一个字符上一次出现的位置
        Map<Character, Integer> prevIdxes = new HashMap<>();
        prevIdxes.put(chars[0], 0);

        // 以i - 1位置字符结尾的最长不重复字符串的开始索引
        int li = 0;
        int max = 1;
        for (int i = 1; i < chars.length; i++) {
            // i位置字符上一次出现的位置
            int pi = prevIdxes.getOrDefault(chars[i], -1);

            if (li <= pi) {
                li = pi + 1;
            }
            // 存储这个字符出现的位置
            prevIdxes.put(chars[i], i);
            // 求出最长不重复子串的长度
            max = Math.max(max, i - li + 1);
        }
        return max;
    }
}
