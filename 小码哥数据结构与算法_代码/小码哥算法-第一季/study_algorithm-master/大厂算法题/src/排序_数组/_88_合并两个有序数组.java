package 排序_数组;

/**
 * https://leetcode-cn.com/problems/merge-sorted-array/
 */
public class _88_合并两个有序数组 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int cur = m + n - 1;
        int i1 = m - 1;
        int i2 = n - 1;

        while (i2 >= 0) {
            if (i1 >= 0 && nums1[i1] > nums2[i2]) {
                nums1[cur--] = nums1[i1--];
            } else { // i1 < 0 || nums2[i2] >= nums1[i1]
                nums1[cur--] = nums2[i2--];
            }
        }
    }
}
