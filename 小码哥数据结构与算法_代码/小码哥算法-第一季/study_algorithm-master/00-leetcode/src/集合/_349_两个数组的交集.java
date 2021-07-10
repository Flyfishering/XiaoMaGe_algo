package 集合;

import java.util.Set;
import java.util.TreeSet;

/**
 * https://leetcode-cn.com/problems/intersection-of-two-arrays/
 */
public class _349_两个数组的交集 {
    public int[] intersection(int[] nums1, int[] nums2) {
        // 换成HashSet会快很多
        Set<Integer> set1 = new TreeSet<>();
        Set<Integer> refSet = new TreeSet<>();
        for (int num : nums1) {
            set1.add(num);
        }

        for (int num : nums2) {
            if (set1.contains(num)) {
                refSet.add(num);
            }
        }

        int[] ref = new int[refSet.size()];
        int i = 0;

        for (int num : refSet) {
            ref[i] = num;
            i++;
        }

        return ref;
    }
}
