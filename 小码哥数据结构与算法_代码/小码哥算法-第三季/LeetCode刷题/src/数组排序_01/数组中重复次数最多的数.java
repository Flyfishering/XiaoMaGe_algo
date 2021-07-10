package 数组排序_01;

import java.util.HashMap;

public class 数组中重复次数最多的数 {
    public static int repetMore(int[] nums) {
        if (nums == null) return 0;
        if (nums.length == 1) return nums[0];
        int maxCount = 1;
        int maxVaue = nums[0];
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                int value = map.get(num);
                value = value + 1;
                if (maxCount < value) {
                    maxCount = value;
                    maxVaue = num;
                }
                map.put(num, value);
            } else {
                map.put(num, 1);
            }
        }
        return maxVaue;
    }

    public static void main(String[] args) {
        数组中重复次数最多的数 o = new 数组中重复次数最多的数();
        o.repetMore(new int[]{1,1,2,3,3,3,3,3,3,3,3,3,3,6,7,1,2,3,2,2,2,2,2,2});
    }
}
