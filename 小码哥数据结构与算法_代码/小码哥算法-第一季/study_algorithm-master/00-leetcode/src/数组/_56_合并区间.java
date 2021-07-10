package 数组;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/merge-intervals/
 * https://mp.weixin.qq.com/s/ioUlNa4ZToCrun3qb4y4Ow
 */
public class _56_合并区间 {
    // 用了动态数组(list,跟官方解比较像)
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (m1, m2) -> m1[0] - m2[0]);

        List<int[]> list = new ArrayList<>();

        for (int[] interval : intervals) {
            if (!list.isEmpty() && interval[0] <= list.get(list.size() - 1)[1]) {
                list.get(list.size() - 1)[1] = Math.max(interval[1], list.get(list.size() - 1)[1]);
            } else {
                list.add(interval);
            }
        }

        return list.toArray(new int[list.size()][2]);
    }

    // 甜姨的写法，用的一个数组，以及指针
    public int[][] merge2(int[][] intervals) {
        // 结果集
        int[][] res = new int[intervals.length][2];
        // 先按照区间起始位置排序
        Arrays.sort(intervals, (m1, m2) -> m1[0] - m2[0]);
        // -1，哨兵。idx指向结果集里面的最后一个数组
        int idx = -1;
        for (int[] interval : intervals) {
            // 如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，说明不重叠。
            // 则不合并，直接将当前区间加入结果数组。
            if (idx == -1 || interval[0] > res[idx][1]) {
                res[++idx] = interval;
            } else {
                // 反之说明重叠，则将当前区间合并至结果数组的最后区间
                res[idx][1] = Math.max(res[idx][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, idx + 1);
    }

}
