package 数组;

public class _80_删除有序数组中的重复项_II {
    public int removeDuplicates(int[] nums) {
        int cur = 1, li = 1, cnt = 1;
        while (li < nums.length) {
            if (nums[li] == nums[li - 1] && cnt >= 2) {
                li++;
            } else if (nums[li] == nums[li - 1] && cnt < 2) {
                nums[cur++] = nums[li++];
                cnt++;
            } else {
                nums[cur++] = nums[li++];
                cnt = 1;
            }
        }
        return cur;
    }

    // 官方题解
    public int removeDuplicates2(int[] nums) {
        if (nums.length < 2) return nums.length;
        int slow = 2, fast = 2;
        while (fast < nums.length) {
            if (nums[slow - 2] != nums[fast]) {
                nums[slow++] = nums[fast];
            }
            fast++;
        }
        return slow;
    }
}
