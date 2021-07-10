package 二分;

public class _154_寻找旋转排序数组中的最小值_II {
    // https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/solution/154-find-minimum-in-rotated-sorted-array-ii-by-jyd/
    // https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/solution/er-fen-fa-fen-zhi-fa-python-dai-ma-by-liweiwei1419/
    public int findMin(int[] nums) {
        int li = 0, ri = nums.length - 1;
        while (li < ri) {
            int mid = (li + ri) >> 1;
            if (nums[mid] < nums[ri]) {
                ri = mid;
            } else if (nums[mid] > nums[ri]) {
                li = mid + 1;
            } else {
                // nums[mid] == nums[ri]时，无法判断。只能ri--，看下一个数能否判断
                ri--;
            }
        }
        return nums[li];
    }
}
