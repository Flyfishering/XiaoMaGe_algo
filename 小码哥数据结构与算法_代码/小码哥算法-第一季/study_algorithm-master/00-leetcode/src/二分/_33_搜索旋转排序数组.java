package 二分;

/**
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
 */
public class _33_搜索旋转排序数组 {
    // 这个做法时间复杂度是O(n)了，不符合题目要求的O(logn)
    public int search(int[] nums, int target) {
        // 两个升序数组[0, k), [k, nums.length)
        int k = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                k = i;
                break;
            }
        }

        int begin = 0, end = nums.length;
        while (begin < end) {
            int mid = begin + ((end - begin) >> 1);
            if (nums[index(mid, k, nums.length)] > target) {
                end = mid;
            } else if (nums[index(mid, k, nums.length)] < target) {
                begin = mid + 1;
            } else {
                return index(mid, k, nums.length);
            }
        }

        return -1;
    }

    /**
     *
     * @param idx 未旋转时的数组索引
     * @return 旋转后的数组索引
     */
    private int index(int idx, int k, int len) {
        if (idx < len - k) {
            idx += k;
        } else {
            idx -= len - k;
        }
        return idx;
    }

    // 要时间O(logn)只有直接二分
    // 二分后的左边跟右边，必有一部分是有序的。利用有序的性质，
    // 看该target是否在有序部分，不然就去另外一部分寻找
    // https://leetcode-cn.com/problems/search-in-rotated-sorted-array/solution/ji-bai-liao-9983de-javayong-hu-by-reedfan/
    public int search2(int[] nums, int target) {
        int begin = 0, end = nums.length;

        while (begin < end) {
            int mid = (begin + end) >> 1;

            if (target == nums[mid]) return mid;

            // 前半部分有序
            // 注意这边的小于等于。为了只有两个元素的时候不出错
            if (nums[begin] <= nums[mid]) {
                // 目标落在前半区间内的话，在前半区间内寻找。否则，去后半区间
                if (target >= nums[begin] && target < nums[mid]) {
                    end = mid;
                } else {
                    begin = mid + 1;
                }
            } else { // 后半部分有序
                // 目标落在后半区间的话，在后半区间寻找。否则，去前半区间
                if (target > nums[mid] && target <= nums[end - 1]) {
                    begin = mid + 1;
                } else {
                    end = mid;
                }
            }
        }

        return -1;
    }
}
