package 数组;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/longest-consecutive-sequence/
 */
public class _128_最长连续序列 {
    // 官方解法。利用set去重，并且只有当元素为左边界时才去计算长度
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int ans = 0;

        for (Integer num : set) {
            // 存在比该元素小的数，表明该元素不是左边界，直接跳过
            if (set.contains(num - 1)) continue;
            int curLen = 1;
            while (set.contains(num + 1)) {
                num++;
                curLen++;
            }
            ans = Math.max(ans, curLen);
        }
        return ans;
    }

    // 利用map，存储以key为边界的连续序列长度。
    // 每拿到一个元素，就去计算新的长度(left + 1 + right)
    // 然后更新回map
    // 需要注意，重复元素不可以进行重复计算，会把值弄错
    // https://leetcode-cn.com/problems/longest-consecutive-sequence/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-3-4/
    public int longestConsecutive2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int num : nums) {
            // 若元素重复，跳过，不然会出错
            //比如 [1 2 1]
            //最后的 1 如果不跳过，因为之前的 2 的最长长度已经更新成 2 了，所以会出错
            if (map.containsKey(num)) continue;
            // 找到以左边数字结尾的最长序列，默认为 0
            int left = map.getOrDefault(num - 1, 0);
            // 找到以右边数开头的最长序列，默认为 0
            int right = map.getOrDefault(num + 1, 0);
            int curLen = left + right + 1;
            ans = Math.max(ans, curLen);

            // 将当前数字放到 map 中，防止重复考虑数字，value 可以随便给一个值
            map.put(num, 0);
            // 更新左边界长度
            map.put(num - left, curLen);
            // 更新右边界长度
            map.put(num + right, curLen);
        }
        return ans;
    }
}
