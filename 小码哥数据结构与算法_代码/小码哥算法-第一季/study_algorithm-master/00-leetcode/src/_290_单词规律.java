import java.util.HashMap;

/**
 * https://leetcode-cn.com/problems/word-pattern/
 */
public class _290_单词规律 {
    public boolean wordPattern(String pattern, String s) {
        String[] split = s.split(" ");
        char[] patternArray = pattern.toCharArray();
        int len = pattern.length();

        if (len != split.length) return false;

        HashMap<Character, String> map1 = new HashMap<>();
        HashMap<String, Character> map2 = new HashMap<>(); // 可以用set进行优化
        for (int i = 0; i < len; i++) {
            if (map1.containsKey(patternArray[i])) {
                if (!map1.get(patternArray[i]).equals(split[i])) return false;
            } else {
                map1.put(patternArray[i], split[i]);
            }
            if (map2.containsKey(split[i])) {
                if (!map2.get(split[i]).equals(patternArray[i])) return false;
            } else {
                map2.put(split[i], patternArray[i]);
            }
        }
        return true;
    }
}
