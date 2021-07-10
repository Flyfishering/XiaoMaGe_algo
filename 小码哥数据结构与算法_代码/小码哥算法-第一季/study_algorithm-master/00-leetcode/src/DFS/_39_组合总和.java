package DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/combination-sum/
 */
public class _39_组合总和 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        dfs(target, candidates, 0, ans, path, 0);
        return ans;
    }

    private void dfs(int target, int[] candidates, int idx, List<List<Integer>> ans, List<Integer> path, int sum) {
        if (sum == target) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = idx; i < candidates.length; i++) {
            int newSum = sum + candidates[i];
            // 剪枝。如果结果已经比目标大了，那就没有继续搜索下去的必要了。
            if (newSum > target) {
                break;
            }
            // 能来到这，表示结果<=目标值，需要继续深搜
            path.add(candidates[i]);
            dfs(target, candidates, i, ans, path, newSum);
            path.remove(path.size() - 1);
        }
    }
}
