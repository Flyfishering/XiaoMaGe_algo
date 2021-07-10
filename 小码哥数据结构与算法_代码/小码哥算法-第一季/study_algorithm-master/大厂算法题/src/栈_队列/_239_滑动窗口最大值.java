package 栈_队列;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/sliding-window-maximum/
 */
public class _239_滑动窗口最大值 {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1) return null;
        if (k == 1) return nums;

        int[] maxes = new int[nums.length - k + 1];
        // 当前滑动窗口的最大值的索引
        int maxIdx = 0;
        // 求出前k个元素的最大索引
        for (int i = 1; i < k; i++) {
            if (nums[i] >= nums[maxIdx]) maxIdx = i;
        }

        // li是滑动窗口的最左索引
      for (int li = 0; li < maxes.length; li++) {
          // ri是滑动窗口的最右索引
          int ri = li + k - 1;
            if (maxIdx < li) { // 最大值的索引不在合理范围内
                maxIdx = li;
                for (int i = li + 1; i <= ri; i++) {
                    if (nums[i] >= nums[maxIdx]) maxIdx = i;
                }
            } else { // 最大值的索引在合理范围内
                if (nums[ri] >= nums[maxIdx]) { // 新的元素比原先的最大值大
                    maxIdx = ri;
                }
            }
            maxes[li] = nums[maxIdx];
        }
        return maxes;
    }

    // 双端队列(单调队列解)
    public int[] maxSlidingWindow_deque(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1) return null;
        if (k == 1) return nums;

        int[] maxes = new int[nums.length - k + 1];

        // 用来存放索引的双端队列。要求队列内的索引对应的值，是按照从大到小排列
        Deque<Integer> deque = new LinkedList<>();

        for (int ri = 0; ri < nums.length; ri++) {
            // 只要nums[队尾] <= nums[i],就删除队尾。(为了维持队列中的元素是从大到小的)
            while (!deque.isEmpty() && nums[ri] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            // 将i加入队尾
            deque.offer(ri);

            // 检查窗口的索引是否合法
            int li = ri - k + 1;
            if (li < 0) continue;

            // 检查队头的合法性
            if (deque.peek() < li) {
                // 队头不合法(失效，队头索引不在滑动窗口范围内)
                deque.poll();
            }
            maxes[li] = nums[deque.peek()];
        }
        return maxes;
    }
}
