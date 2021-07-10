package 高频题;

/**
 * https://leetcode-cn.com/problems/move-zeroes/
 */
public class _283_移动零 {
    // 小码哥的解法
    public void moveZeroes(int[] nums) {
        for (int i = 0, cur = 0; i < nums.length; i++) {
            if (nums[i] == 0) continue;
            if (cur != i) {
                nums[cur] = nums[i];
                nums[i] = 0;
            }
            cur++;
        }
    }

    // 自己的解法(跟三色问题一样的思路)
    public void moveZeroes2(int[] nums) {
        int i = 0;
        // cur指向的位置表示可以用来存放非0元素
        int cur = 0;

        while (i < nums.length) {
            if (nums[i] == 0) {
                i++;
            } else {
                int tmp = nums[cur];
                nums[cur] = nums[i];
                nums[i] = tmp;
                i++;
                cur++;
            }
        }
    }
}
