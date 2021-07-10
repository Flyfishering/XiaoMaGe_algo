package 栈队列_03;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/*
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/sliding-window-maximum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
public class _24_239_滑动窗口最大值 {
    /*
    * 解法1:利用双端队列,对每一次遍历到的值进行检验后入队,取值时从队头出队
    * */
    public int[] maxSlidingWindow_queue(int[] nums, int k) {
        if (nums.length == 0 || nums == null || k < 1) return new int[0];
        if (k == 1) return nums;

        Deque<Integer> deque = new LinkedList<>();
        int[] maxes = new int[nums.length - k + 1];

        for (int ri = 0; ri <= nums.length - 1 ; ri++) {
            while (!deque.isEmpty() && nums[ri] >= nums[deque.peekLast()] ) {
                deque.removeLast();
            }

            deque.addLast(ri);

            int li = ri - k + 1;
            if (li < 0) continue;

            // 检查对头的合法性
            if (deque.peekFirst() < li) {
                // 队头不合法（失效，不在滑动窗口索引范围内）
                deque.removeFirst();
            }

            maxes[li] = nums[deque.peekFirst()];
        }
        return maxes;
    }

    /*
    * 解法2:这种方式目前会超时
    * */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || nums == null || k < 1) return new int[0];
        if (k == 1) return nums;

        int[] maxes = new int[nums.length - k + 1];

        // 先求出前k个元素的最大值
        int maxIdx = 0;
        for (int i = 1; i < k ; i++) {
            if (nums[maxIdx] < nums[i]) maxIdx = i;
        }

        // li是滑动窗口的最左
        for (int li = 0; li < maxes.length; li++) {
            int ri = li + k - 1; // ri是滑动窗口的最最右
            if (maxIdx < li) {  // 之前最大值得索引已经不在滑动窗口的范围
                // 求出[li,ri]范围内的最大值索引
                maxIdx = li;
                for (int i = li + 1; i <= ri; i++) {
                    if (nums[i] > nums[maxIdx]) maxIdx = i;
                }
            } else if(nums[maxIdx] <= nums[ri]) { // 当前最大值的索引在滑动窗口范围内,向右滑动时,新的值比当前最大值还要大,更新最大值的索引
                maxIdx = ri;
            }
            maxes[li] = nums[maxIdx];
        }
        return maxes;
    }
}
