package 数组;

/**
 * https://leetcode-cn.com/problems/merge-sorted-array/
 */
public class _88_合并两个有序数组 {
    // 利用一个数组备份nums1的值
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) return;
        int[] nums1Bak = new int[m];
        for (int i = 0; i < m; i++) {
            nums1Bak[i] = nums1[i];
        }
        int num1Index = 0;
        int num2Index = 0;
        int ai = 0;
        while (num1Index < m || num2Index < n) {
            if (num1Index < m && num2Index < n) {
                if (nums1Bak[num1Index] <= nums2[num2Index]) {
                    nums1[ai++] = nums1Bak[num1Index++];
                } else {
                    nums1[ai++] = nums2[num2Index++];
                }
            } else if (num1Index < m) {
                nums1[ai++] = nums1Bak[num1Index++];
            } else {
                nums1[ai++] = nums2[num2Index++];
            }
        }
    }

    // 从大到小进行排序
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        if (m == 0) {
            for (int i = 0; i < n; i++) {
                nums1[i] = nums2[i];
            }
            return;
        }
        int begin = m + n - 1;
        int nums1Begin = m - 1;
        int nums2Begin = n - 1;
        while (nums2Begin >= 0) {
            if (nums1Begin >= 0 && nums2[nums2Begin] < nums1[nums1Begin]) {
                nums1[begin--] = nums1[nums1Begin--];
            } else {
                nums1[begin--] = nums2[nums2Begin--];
            }
        }
    }

    public void merge3(int[] nums1, int m, int[] nums2, int n) {
        int index = m + n - 1;
        int nums1Begin = m - 1;
        int nums2Begin = n - 1;

        while (nums2Begin >= 0) {
            if (nums1Begin >= 0 && nums1[nums1Begin] > nums2[nums2Begin]) {
                nums1[index--] = nums1[nums1Begin--];
            } else {
                nums1[index--] = nums2[nums2Begin--];
            }
        }
    }
}
