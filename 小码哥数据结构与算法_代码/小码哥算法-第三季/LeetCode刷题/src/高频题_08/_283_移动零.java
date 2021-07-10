package 高频题_08;

/*
* https://leetcode-cn.com/problems/move-zeroes/
* */
public class _283_移动零 {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;
        for (int i = 0,cur = 0; i < nums.length; i++) {
            if (nums[i] == 0) continue;
            if (cur != i) { // 一定要加这个判断,如果不加,假如一开始两者都在0位置,而0位置的元素是5,在nums[i]=0语句执行后,就把5改为0了
                nums[cur] = nums[i];
                nums[i] = 0;
            }
            cur++;
        }
    }
}
