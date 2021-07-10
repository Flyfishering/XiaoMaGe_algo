package 数组;

/**
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 */
public class _26_删除有序数组中的重复项 {
    // 官方题解
    public int removeDuplicates2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        // j用来遍历数组。当nums[i] != nums[j]的时候，就用nums[j]覆盖掉nums[i+1]
        // 到索引i为止存放的都是不重复的数
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                nums[++i] = nums[j];
            }
        }
        return i + 1;
    }
}
