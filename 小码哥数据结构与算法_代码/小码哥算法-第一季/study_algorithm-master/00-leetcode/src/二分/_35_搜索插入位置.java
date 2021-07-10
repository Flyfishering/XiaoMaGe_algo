package 二分;

public class _35_搜索插入位置 {
    // 要找的，是第一个大于等于target的数组元素的索引
    public int searchInsert(int[] nums, int target) {
        int li = 0, ri = nums.length - 1;
        // 特殊判断
        if (target > nums[ri]) return nums.length;
        // 程序走到这里一定有 target <= nums[len - 1]
        // 在区间 nums[left..right] 里查找第 1 个大于等于 target 的元素的下标
        while (li < ri) {
            int mid = (li + ri) >> 1;
            // 如果当前mid看到的数值严格小于target，那么mid以及mid左边的所有元素就一定不是题目要求的输出
            if (nums[mid] < target) {
                // 下一轮搜索的区间是 [mid + 1..right]
                li = mid + 1;
            } else {
                // 下一轮搜索的区间是 [left..mid]
                ri = mid;
            }
        }
        return li;
    }

    public int searchInsert2(int[] nums, int target) {
        int li = 0, ri = nums.length;
        while (li < ri) {
            int mid = (li + ri) >> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                li = mid + 1;
            } else {
                ri = mid;
            }
        }
        return li;
    }

    public static void main(String[] args) {
        _35_搜索插入位置 test = new _35_搜索插入位置();
        System.out.println(test.searchInsert(new int[] {1, 3, 5, 6}, 2));
    }
}
