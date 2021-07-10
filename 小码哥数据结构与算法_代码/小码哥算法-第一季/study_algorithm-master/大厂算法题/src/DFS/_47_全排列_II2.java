package DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/permutations-ii/
 */
public class _47_全排列_II2 {
    int[] nums;
    List<List<Integer>> list;

    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null) return null;
        list = new ArrayList<>();
        if (nums.length == 0) return list;
        this.nums = nums;

        dfs(0);

        return list;
    }

    private void dfs(int idx) {
        if (idx == nums.length) {
            List<Integer> resList = new ArrayList<>();
            for(int res : nums) {
                resList.add(res);
            }
            list.add(resList);
            return;
        }

        // 枚举这一层所有可以做出的选择
        for (int i = idx; i < nums.length; i++) {
            // 要保证一个数字在idx位置只会出现一次
            if (isRepeat(idx, i)) continue;
            swap(idx, i);
            dfs(idx + 1);
            swap(idx, i);
        }
    }

    private boolean isRepeat(int idx, int i) {
        for (int j = idx; j < i; j++) {
            if (nums[j] == nums[i]) return true;
        }
        return false;
    }

    private void swap(int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
