package 数组;

/**
 * https://leetcode-cn.com/problems/next-permutation/
 */
public class _31_下一个排列 {
    // https://leetcode-cn.com/problems/next-permutation/solution/jie-fa-hen-jian-dan-jie-shi-qi-lai-zen-yao-jiu-na-/
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        // 从右往左遍历，找到第一个右边比左边大的，左边的元素
        for (; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) break;
        }
        // 如果这个元素存在
        if (i >= 0) {
            // 在右边找到第一个比这个元素大的元素
            int j = nums.length - 1;
            for (; j > i; j--) {
                if (nums[j] > nums[i]) break;
            }
            swap(nums, i, j);
        }
        // i右边肯定是降序，为了让这个数小一点，翻转，改成升序
        // 至于为什么是降序，因为nums[i]<nums[i+1]这个条件
        // 找到i才成立
        int l = i + 1, r = nums.length - 1;
        while (l < r) {
            swap(nums, l, r);
            l++;
            r--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    // 换成while循环。。感觉更好看一点
    public void nextPermutation2(int[] nums) {
        int i = nums.length - 2;    // 向左遍历，i从倒数第二开始是为了nums[i+1]要存在
        while (i >= 0 && nums[i] >= nums[i + 1]) { // 寻找第一个小于右邻居的数
            i--;
        }
        if (i >= 0) {   // 这个数在数组中存在，从它身后挑一个数，和它换
            int j = nums.length - 1;    // 从最后一项，向左遍历
            while (j > i && nums[j] <= nums[i]) {   // 寻找第一个大于 nums[i] 的数
                j--;
            }
            swap(nums, i, j);   // 两数交换，实现变大
        }
        // 如果 i = -1，说明是递减排列，如 3 2 1，没有下一排列，直接翻转为最小排列：1 2 3
        int l = i + 1, r = nums.length - 1;
        while (l < r) {
            swap(nums, l++, r--);   // i 右边的数进行翻转，使得变大的幅度小一些
        }
    }
}
