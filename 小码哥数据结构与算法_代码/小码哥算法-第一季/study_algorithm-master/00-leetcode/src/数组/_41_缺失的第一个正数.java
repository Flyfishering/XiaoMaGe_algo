package 数组;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/first-missing-positive/
 */
public class _41_缺失的第一个正数 {
    // 利用hash表。空间复杂度O(n)
    public int firstMissingPositive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for (int i = 1; i <= nums.length; i++) {
            if (set.contains(i)) continue;
            return i;
        }
        return nums.length + 1;
    }

    // 原地置换。思路参考剑指offer03
    public int firstMissingPositive2(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            // 希望的数组：nums:1, 2, 3, 4, 5 ...
            //                0, 1, 2, 3, 4 ...
            // 将1放置在0这个位置，2放置在1这个位置
            // 即，将nums[i]这个值 放置在nums[i] - 1这个索引
            // 可以交换的nums[i]取值范围[1, nums.length]
            while (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {
                // 对i位置的元素和(nums[i] - 1)位置的元素做交换(因为i位置的元素，应该放置到(nums[i] - 1)这个位置)
                // 如果i位置的元素已经和(nums[i] - 1)位置的元素相等了，那么无需交换
                int tmp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = tmp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) return i + 1;
        }
        return nums.length + 1;
    }
}
