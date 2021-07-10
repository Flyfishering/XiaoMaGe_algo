package 设计;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/insert-delete-getrandom-o1/
 */
public class _380_常数时间插入_删除和获取随机元素 {
    class RandomizedSet {
        List<Integer> list;
        Map<Integer, Integer> map;

        /** Initialize your data structure here. */
        public RandomizedSet() {
            list = new ArrayList<>();
            map = new HashMap<>();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if (!map.containsKey(val)) {
                list.add(val);
                map.put(val, list.size() - 1);
                return true;
            }
            return false;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            int idx = map.getOrDefault(val, -1);
            if (idx == -1) return false;
            int lastElement = list.get(list.size() - 1);

            list.set(idx, lastElement);
            map.put(lastElement, idx);

            list.remove(list.size() - 1);
            map.remove(val);
            return true;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            int idx = (int) (Math.random() * list.size());
            return list.get(idx);
        }
    }
}
