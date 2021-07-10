package 数组;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/find-k-closest-elements/
 */
public class _658_找到_K_个最接近的元素 {
    // https://leetcode-cn.com/problems/find-k-closest-elements/solution/pai-chu-fa-shuang-zhi-zhen-er-fen-fa-python-dai-ma/
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int l = 0, r = arr.length - 1;
        int len = r - l + 1;
        while (len > k) {
            int left = Math.abs(arr[l] - x);
            int right = Math.abs(arr[r] - x);
            if (left <= right) {
                r--;
            } else {
                l++;
            }
            len--;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = l; i <= r; i++) {
            ans.add(arr[i]);
        }
        return ans;
    }

    // 二分法。理解的头大。多写几遍吧。。
    // 二分法的核心，是排除。减而治之
    public List<Integer> findClosestElements2(int[] arr, int k, int x) {
        // 左边界取值范围[0, length - k]
        int l = 0, r = arr.length - k;

        // 针对左边界位于的区间进行二分。
        while (l < r) {
            int mid = (l + r) >> 1;
            // x在[mid, mid + k]这个区间内，更加偏向[mid + k](右边界)时，
            // 左边界收缩。同时由于[mid, mid + k]有k + 1个元素
            // l从mid + 1开始就可以了
            // 下面这个条件可以这样理解。
            // x > (mid + (mid + k)) / 2
            // x离右边界比较近
            if (x - arr[mid] > arr[mid + k] - x) {
                l = mid + 1;
            } else {
                // x更加偏向[mid](左边界)时，
                // 右边界收缩。r = mid;
                // 注意，[mid] == [mid + k]时，
                // 由于题目要求的是较小的那个数，
                // 所以右边界收缩
                r = mid;
            }
        }
        List<Integer> ans = new ArrayList<>();
        int end = l + k;
        // [l, end)
        for (int i = l; i < end; i++) {
            ans.add(arr[i]);
        }
        return ans;
    }
}
