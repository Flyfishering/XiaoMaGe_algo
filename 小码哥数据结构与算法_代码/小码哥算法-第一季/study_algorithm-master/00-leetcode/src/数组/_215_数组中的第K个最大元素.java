package 数组;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 */
public class _215_数组中的第K个最大元素 {
    // 快速排序
    public int findKthLargest(int[] nums, int k) {
        int l = 0, r = nums.length - 1;
        int target = nums.length - k;
        while (true) {
            int index = partition(nums, l, r);
            if (index == target) {
                return nums[index];
            } else if (index < target) {
                l = index + 1;
            } else {
                r = index - 1;
            }
        }
    }

    private int partition(int[] nums, int l, int r) {
        int index = (int) (Math.random() * (r - l + 1)) + l;
        swap(nums, l, index);
        int pivot = nums[l];

        while (l < r) {
            while (l < r) {
                if (nums[r] > pivot) {
                    r--;
                } else {
                    nums[l++] = nums[r];
                    break;
                }
            }
            while (l < r) {
                if (nums[l] < pivot) {
                    l++;
                } else {
                    nums[r--] = nums[l];
                    break;
                }
            }
        }
        nums[l] = pivot;
        return l;
    }

    private void swap(int[] nums, int l, int index) {
        int tmp = nums[l];
        nums[l] = nums[index];
        nums[index] = tmp;
    }

    // 维持k个元素的小顶堆
    public int findKthLargest2(int[] nums, int k) {
        Queue<Integer> queue = new PriorityQueue<>(k);

        for (int i = 0; i < k; i++) {
            queue.offer(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            int top = queue.peek();
            if (nums[i] > top) {
                queue.poll();
                queue.offer(nums[i]);
            }
        }
        return queue.peek();
    }
}
