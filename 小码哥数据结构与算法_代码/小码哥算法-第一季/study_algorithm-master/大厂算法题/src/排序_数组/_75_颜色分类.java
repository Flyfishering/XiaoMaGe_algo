package 排序_数组;

/**
 * https://leetcode-cn.com/problems/sort-colors/
 */
public class _75_颜色分类 {
    // 一个只包含0，1，2的整型数组，对他进行原地排序
    // 要求常数空间，扫描一遍
    // 即空间O(1), 时间O(n)
    public void sortColors(int[] nums) {
        int i = 0;
        int l = 0;
        int r = nums.length - 1;

        while (i <= r) {
            int v = nums[i];
            if (v == 2) {
                swap(nums, i, r--);
            } else if (v == 0) {
                swap(nums, i++, l++);
            } else {
                i++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
