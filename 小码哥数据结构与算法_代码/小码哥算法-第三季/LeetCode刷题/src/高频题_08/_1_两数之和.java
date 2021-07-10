package 高频题_08;

import java.util.HashMap;

/*
* https://leetcode-cn.com/problems/two-sum/submissions/
* */
public class _1_两数之和 {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null) return null;
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer idx = map.get(target - nums[i]);
            if (idx != null) return  new int[]{idx,i};
            map.put(nums[i],i);
        }
        return null;
    }
}
