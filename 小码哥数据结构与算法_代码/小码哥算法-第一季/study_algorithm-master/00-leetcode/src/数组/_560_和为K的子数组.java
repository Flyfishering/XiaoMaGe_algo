package 数组;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/subarray-sum-equals-k/
 * https://leetcode-cn.com/problems/subarray-sum-equals-k/solution/bao-li-jie-fa-qian-zhui-he-qian-zhui-he-you-hua-ja/
 */
public class _560_和为K的子数组 {
    // 暴力法 O(n^2)
    public int subarraySum(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) ans++;
            }
        }
        return ans;
    }

    // 前缀和 O(n^2)
    public int subarraySum2(int[] nums, int k) {
        int[] preSum = new int[nums.length + 1];
        preSum[0] = 0;
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }

        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum = preSum[j + 1] - preSum[i];
                if (sum == k) ans++;
            }
        }
        return ans;
    }

    // 前缀和+hashmap优化。思想跟两数之和是相似的
    public int subarraySum3(int[] nums, int k) {
        // 用来记录当前索引对饮的前缀和
        int presum = 0;
        // key:前缀和，value:该前缀和出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        // 前缀和为0，假定出现了一次。为的是统一操作。例如，k = 3时，[3,....]或者[1,1,1,...]
        map.put(0, 1);
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            presum += nums[i];
            // [0,presum - k] + [presum - k, presum] = presum
            if (map.containsKey(presum - k)) {
                ans += map.get(presum - k);
            }

            map.put(presum, map.getOrDefault(presum, 0) + 1);
        }
        return ans;
    }
}
