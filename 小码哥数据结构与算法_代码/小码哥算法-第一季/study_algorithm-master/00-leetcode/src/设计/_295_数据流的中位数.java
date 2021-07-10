package 设计;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/find-median-from-data-stream/
 */
public class _295_数据流的中位数 {
    // 利用堆。数据流为偶数的时候，最大堆的最大值为左中位数，最小堆的最小值为右中位数。最大堆与最小堆元素数量相同
    // 数据流为奇数的时候，最大堆的最大值为中位数。最大堆元素数量比最小堆多1
    // https://leetcode-cn.com/problems/find-median-from-data-stream/solution/you-xian-dui-lie-python-dai-ma-java-dai-ma-by-liwe/
    class MedianFinder {
        // 最大堆
        Queue<Integer> maxHeap;
        // 最小堆
        Queue<Integer> minHeap;
        // 数据流的元素数量
        int count;


        /** initialize your data structure here. */
        public MedianFinder() {
            maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
            minHeap = new PriorityQueue<>();
            count = 0;
        }

        public void addNum(int num) {
            count++;
            maxHeap.add(num);
            minHeap.add(maxHeap.poll());
            // 奇数的时候，要保证最大堆比最小堆多一个元素
            if ((count & 1) == 1) {
                maxHeap.add(minHeap.poll());
            }
        }

        public double findMedian() {
            // 元素数量为奇数，最大堆的最大值即为中位数
            if ((count & 1) == 1) {
                return maxHeap.peek();
            } else {
                // 偶数的场合，最大堆的最大值与最小堆的最小值的和，再除以2，即为中位数
                return ((double) (maxHeap.peek() + minHeap.peek())) / 2;
            }
        }
    }
}
