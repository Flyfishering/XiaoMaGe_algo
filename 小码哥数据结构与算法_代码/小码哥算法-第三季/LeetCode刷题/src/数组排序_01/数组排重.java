package 数组排序_01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class 数组排重 {
    public static List<Integer> removeRepert(int[] nums) {
        List<Integer> res = new ArrayList<>();
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i],i);
                res.add(nums[i]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        数组排重 o = new 数组排重();
        o.removeRepert(new int[]{1,1,1,2,3,4,5,6,1,2,3,4,4,4,8,7,7});
    }
}
