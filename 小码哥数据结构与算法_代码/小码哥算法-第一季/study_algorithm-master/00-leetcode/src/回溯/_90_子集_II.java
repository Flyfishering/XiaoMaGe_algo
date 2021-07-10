package 回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/subsets-ii/
 */
public class _90_子集_II {
    // 回溯(与第78道题基本是一样的思路，只是多了一步剪枝)
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums == null) return null;
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) return res;

        List<Integer> list = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);

        dfs(0, nums, list, res, used);

        return res;
    }

    private void dfs(int idx, int[] nums, List<Integer> list, List<List<Integer>> res, boolean[] used) {
        res.add(new ArrayList<>(list));

        for (int i = idx; i < nums.length; i++) {
            // 注意这里的剪枝条件。除了这个条件，这题跟No.78的子集是完全一样的
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            // 当前层的第二个以及之后的数要用的时候，发现跟前面的数相同，即可跳过。
            // 这么写可以节约空间，以及判断次数
            //if (i > idx && nums[i] == nums[i - 1]) continue;
            list.add(nums[i]);
            used[i] = true;
            dfs(i + 1, nums, list, res, used);
            used[i] = false;
            list.remove(list.size() - 1);
        }
    }

    // 利用每个数在子集中只有两种状态(存在，不存在)来dfs
    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        if (nums == null) return null;
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) return res;

        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);

        dfs2(0, nums, list, res, false);

        return res;
    }

    /**
     *
     * @param idx 层数
     * @param choosePrev 上一层是否有选择。有的话，true，没有的话，false
     */
    private void dfs2(int idx, int[] nums, List<Integer> list, List<List<Integer>> res, boolean choosePrev) {
        if (idx == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        // 不用当前的数，进入下一层
        dfs2(idx + 1, nums, list, res, false);

        // 若发现没有选择上一个数，且当前数字与上一个数相同，则跳过(因为会重复)
        if (!choosePrev && idx > 0 && nums[idx] == nums[idx - 1]) {
            return;
        }
        list.add(nums[idx]);
        dfs2(idx + 1, nums, list, res, true);
        list.remove(list.size() - 1);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        System.out.println(new _90_子集_II().subsetsWithDup(nums));
    }
}
