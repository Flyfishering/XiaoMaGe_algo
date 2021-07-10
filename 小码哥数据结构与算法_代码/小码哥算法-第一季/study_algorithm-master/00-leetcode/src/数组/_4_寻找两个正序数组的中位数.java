package 数组;

/**
 *
 */
public class _4_寻找两个正序数组的中位数 {
    // https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-2/
    // 解法2，时间复杂度O(m + n)，不满足O(log(m + n))
    // 中位数，奇数个数的时候就是中间那个数，偶数个时是中间两个数的和除以2
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length, left = 0, right = 0;
        int li = 0, ri = 0;
        int len = ((m + n) >> 1) + 1, index = 0;
        while (index < len) {
            while (index < len && li < m && (ri == n || nums1[li] <= nums2[ri])) {
                left = right;
                right = nums1[li++];
                index++;
            }
            while (index < len && ri < n && (li == m || nums2[ri] < nums1[li])) {
                left = right;
                right = nums2[ri++];
                index++;
            }
        }

        return ((m + n) & 1) == 0 ? ((double) (left + right)) / 2 : right;
    }

    // 解法3，通过二分法来优化。
    // 解法4(找中位数的分界线？)太过复杂，不去理解了
    // 这里参照的是官方题解。因为不想用递归
    // https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xun-zhao-liang-ge-you-xu-shu-zu-de-zhong-wei-s-114/
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length;
        int totalLength = length1 + length2;
        // 总长度为奇数的情况
        if ((totalLength & 1) == 1) {
            int midIndex = totalLength >> 1;
            return getKthElement(nums1, nums2, midIndex + 1);
        } else { // 总长度为偶数的情况
            int midIndex1 = (totalLength >> 1) - 1, midIndex2 = midIndex1 + 1;
            return (double) ((getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1))) / 2;
        }
    }

    // 在nums1和nums2中找寻第k个小的元素(nums1和2都是升序数组)
    private int getKthElement(int[] nums1, int[] nums2, int k) {
        int index1 = 0, index2 = 0;
        int length1 = nums1.length, length2 = nums2.length;
        while (true) {
            // nums1已为空
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            // nums2已为空
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            // 当k只有1的时候，找到最小的元素直接返回
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }

            // 利用二分进行快速查找
            int half = k >> 1;
            // 根据half算出新的索引。注意，索引不能越界
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1;
            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];

            if (pivot1 <= pivot2) {
                // 舍弃nums1的[index1, newIndex1]这部分
                k -= newIndex1 - index1 + 1;
                index1 = newIndex1 + 1;
            } else {
                // 舍弃nums2的[index2, newIndex2]这部分
                k -= newIndex2 - index2 + 1;
                index2 = newIndex2 + 1;
            }
        }
    }
}
