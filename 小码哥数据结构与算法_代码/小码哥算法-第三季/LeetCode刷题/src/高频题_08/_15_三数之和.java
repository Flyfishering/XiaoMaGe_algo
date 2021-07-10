package 高频题_08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* https://leetcode-cn.com/problems/3sum/
* */
public class _15_三数之和 {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null) return null;

        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 3) return res;

        Arrays.sort(nums);

        int lastIdx = nums.length - 3;
        int rIdx = nums.length - 1;
        for (int i = 0; i <= lastIdx; i++) {
            // 去重
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int l = i + 1;
            int r = rIdx;
            int remain = -nums[i]; // 还差多少能够凑成0,其实就是 0 - nums[i]
            while (l < r) {
                if (nums[l] + nums[r] == remain) {
                    while (l < r && nums[l] == nums[l+1]) l++;
                    while (l < r && nums[r] == nums[r-1]) r--;
                    res.add(Arrays.asList(nums[i],nums[l],nums[r]));
                    l++;
                    r--;
                } else if (nums[l] + nums[r] < remain) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return res;
    }
}
