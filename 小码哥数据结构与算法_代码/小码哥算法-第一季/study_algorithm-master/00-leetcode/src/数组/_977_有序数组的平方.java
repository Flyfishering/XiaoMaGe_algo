package 数组;

public class _977_有序数组的平方 {
    // 双指针，直接两头开始(相当于两个有序序列，都从末尾开始比较，合并)
    public int[] sortedSquares(int[] nums) {
        int beginIdx = 0;
        int endIdx = nums.length - 1;
        int idx = nums.length - 1;
        int[] ans = new int[nums.length];

        while (beginIdx <= endIdx) {
            int beginVal = nums[beginIdx] * nums[beginIdx];
            int endVal = nums[endIdx] * nums[endIdx];
            if (endVal >= beginVal) {
                ans[idx--] = endVal;
                endIdx--;
            } else {
                ans[idx--] = beginVal;
                beginIdx++;
            }
        }
        return ans;
    }

    // 双指针，类似归并排序的merge(相当于两个有序序列，都从起点开始比较，合并)
    public int[] sortedSquares2(int[] nums) {
        int minusIdx = 0;
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                minusIdx = i;
            } else {
                break;
            }
        }
        int idx = minusIdx + 1;
        int cur = 0;
        while (minusIdx >= 0 && idx < nums.length) {
            if (Math.abs(nums[minusIdx]) <= nums[idx]) {
                ans[cur++] = nums[minusIdx] * nums[minusIdx];
                minusIdx--;
            } else {
                ans[cur++] = nums[idx] * nums[idx];
                idx++;
            }
        }
        while (minusIdx >= 0) {
            ans[cur++] = nums[minusIdx] * nums[minusIdx];
            minusIdx--;
        }
        while (idx < nums.length) {
            ans[cur++] = nums[idx] * nums[idx];
            idx++;
        }
        return ans;
    }
}
