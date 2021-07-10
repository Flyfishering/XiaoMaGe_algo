package 回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/subsets/
 */
public class _78_子集 {
    // 利用每个数在子集中只有两种状态(存在，不存在)来dfs
    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null) return null;
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) return res;
        List<Integer> resList = new ArrayList<>();

        dfs(0, nums, res, resList);

        return res;
    }

    private void dfs(int idx, int[] nums, List<List<Integer>> res, List<Integer> resList) {
        if (idx == nums.length) {
            res.add(new ArrayList<>(resList));
            return;
        }
        resList.add(nums[idx]);
        dfs(idx + 1, nums, res, resList);
        resList.remove(resList.size() - 1);
        dfs(idx + 1, nums, res, resList);
    }


    // 回溯
    public List<List<Integer>> subsets2(int[] nums) {
        if (nums == null) return null;
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) return res;
        List<Integer> resList = new ArrayList<>();

        dfs2(0, nums, res, resList);

        return res;
    }

    private void dfs2(int idx, int[] nums, List<List<Integer>> res, List<Integer> resList) {
        res.add(new ArrayList<>(resList));

        for (int i = idx; i < nums.length; i++) {
            resList.add(nums[i]);
            dfs2(i + 1, nums, res, resList);
            resList.remove(resList.size() - 1);
        }
    }
}
