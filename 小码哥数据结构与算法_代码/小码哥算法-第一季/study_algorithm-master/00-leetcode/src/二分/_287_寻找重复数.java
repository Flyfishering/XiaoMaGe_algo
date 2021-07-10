package 二分;

/**
 * https://leetcode-cn.com/problems/find-the-duplicate-number/
 */
public class _287_寻找重复数 {
    // 二分法，抽屉原理
    public int findDuplicate(int[] nums) {
        int begin = 1;
        int end = nums.length - 1;

        while (begin < end) {
            int mid = (begin + end) >> 1;
            int count = 0;
            for (int num : nums) {
                if (num <= mid) {
                    count++;
                }
            }
            if (count > mid) {
                end = mid;
            } else {
                begin = mid + 1;
            }
        }
        return begin;
    }
}
