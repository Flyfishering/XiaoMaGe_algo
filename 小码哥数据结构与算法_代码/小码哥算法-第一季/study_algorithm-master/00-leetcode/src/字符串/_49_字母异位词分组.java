package 字符串;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/group-anagrams/
 */
public class _49_字母异位词分组 {
    // 对字符串做排序，排序后用作hash表的key
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null) return null;
        if (strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] cs = str.toCharArray();
            Arrays.sort(cs);
            String key = new String(cs);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }

        return new ArrayList<>(map.values());
    }

    // 将字符串重新编码，再用作hash表的key
    // 如，[b,a,a,a,b,c] 编码成 a3b2c1
    public List<List<String>> groupAnagrams2(String[] strs) {
        if (strs == null) return null;
        if (strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();


        for (String str : strs) {
            char[] cs = str.toCharArray();
            int[] counts = new int[26];
            for (int i = 0; i < cs.length; i++) {
                counts[cs[i] - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            for (int i = 0; i < counts.length; i++) {
                if (counts[i] != 0) {
                    // 加入字符
                    sb.append((char) ('a' + i));
                    // 加入字符数量
                    sb.append(counts[i]);
                }
            }
            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }
}
