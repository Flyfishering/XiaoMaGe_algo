package DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/permutations-ii/
 */
public class _47_全排列_II1 {
    int[] nums;
    // 用来保存每一层选择的数字
    int[] result;
    List<List<Integer>> list;
    // 用来标记nums中的每一个数字，是否被使用过了
    boolean[] used;

    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null) return null;
        list = new ArrayList<>();
        if (nums.length == 0) return list;

        result = new int[nums.length];
        Arrays.sort(nums);
        this.nums = nums;
        used = new boolean[nums.length];

        dfs(0);

        return list;
    }

    private void dfs(int idx) {
        if (idx == nums.length) {
            List<Integer> resList = new ArrayList<>();
            for(int res : result) {
                resList.add(res);
            }
            list.add(resList);
            return;
        }

        // 枚举这一层所有可以做出的选择
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            result[idx] = nums[i];
            used[i] = true;
            dfs(idx + 1);
            // 还原现场
            used[i] = false;
        }
    }
}
