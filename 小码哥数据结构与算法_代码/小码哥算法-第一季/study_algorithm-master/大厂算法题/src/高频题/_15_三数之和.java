package 高频题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/3sum/
 */
public class _15_三数之和 {
    public List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 3) return res;

        // 排序
        Arrays.sort(nums);

        // i用来扫描三元组的第一个元素
        for (int i = 0; i < len - 2; i++) {
            // 去重
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int r = len - 1;
            int l = i + 1;
            int remain = -nums[i];

            while (l < r) {
                int sumLr = nums[l] + nums[r];
                // 找到了符合条件的三元组
                if (sumLr == remain) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    // 跳过相同的值(去重)
                    while (l < r && nums[l] == nums[l + 1]) l++;
                    while (l < r && nums[r] == nums[r - 1]) r--;
                    // 往中间逼近
                    l++;
                    r--;
                } else if (sumLr < remain) {
                    while (l < r && nums[l] == nums[l + 1]) l++;
                    l++;
                } else { // sumLr > remain
                    while (l < r && nums[r] == nums[r - 1]) r--;
                    r--;
                }
            }
        }
        return res;
    }
}
