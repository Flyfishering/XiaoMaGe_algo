package 数组;

/**
 * https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/
 */
public class _剑指_Offer_03_数组中重复的数字 {
    // 哈希表(数组代替)，时间O(n)，空间O(n)
    public int findRepeatNumber(int[] nums) {
        int[] counts = new int[nums.length];
        for (int num : nums) {
            if (counts[num] != 0) return num;
            counts[num]++;
        }
        return 0;
    }

    // 原地置换
    // 一个萝卜一个坑
    // https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/solution/yuan-di-zhi-huan-shi-jian-kong-jian-100-by-derrick/
    public int findRepeatNumber2(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                int tmp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = tmp;
            }
        }
        return -1;
    }
}
