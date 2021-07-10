package 数组;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/assign-cookies/
 * TODO
 */
public class _455_分发饼干 {
    public int findContentChildren(int[] g, int[] s) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : s) {
            Integer count = map.get(i);
            if (count == null) {
                map.put(i, 1);
            } else {
                map.put(i, count + 1);
            }
        }

        for (int i : g) {

        }
        return -1;
    }
}
