package 二分;

/**
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/
 */
public class _81_搜索旋转排序数组_II {
    public boolean search(int[] nums, int target) {
        int li = 0, ri = nums.length;
        // [li, ri)
        while (li < ri) {
            int mid = li + ((ri - li) >> 1);
            if (target == nums[mid]) return true;
            // 由于本体可以有重复数据，nums[li]与nums[mid]相等时
            // 无法判断左边还是右边有序。所以只能舍去li，取下一个值重新判断
            if (nums[li] == nums[mid]) {
                li++;
                continue;
            }
            // 左半部分严格有序
            if (nums[li] < nums[mid]) {
                if (target >= nums[li] && target < nums[mid]) {
                    ri = mid;
                } else {
                    li = mid + 1;
                }
            } else { // 右半部分严格有序
                if (target > nums[mid] && target <= nums[ri - 1]) {
                    li = mid + 1;
                } else {
                    ri = mid;
                }
            }
        }
        return false;
    }

}
