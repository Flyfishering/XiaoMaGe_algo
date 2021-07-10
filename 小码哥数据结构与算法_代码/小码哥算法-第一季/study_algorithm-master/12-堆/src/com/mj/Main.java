package com.mj;

import com.mj.heap.BinaryHeap;

import java.util.Comparator;

public class Main {

    // TopK问题
    public static void test1() {
        // 通过小顶堆实现
        BinaryHeap<Integer> heap = new BinaryHeap<>(new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        // 找出最大的前k个数
        int k = 5;

        Integer[] data = {5, 60, 98, 38, 69, 77, 99, 25, 63, 87, 23, 11, 90};
        // O(n*logk)
        for (int i = 0; i < data.length; i++) {
            if (heap.size() < k) { // 前k个数添加到小顶堆
                heap.add(data[i]); // logk
            } else if (data[i] > heap.get()) { // 如果是第k+1个元素,并且大于堆顶元素
                    heap.replace(data[i]); // logk
            }
        }
    }

    public static void main(String args[]) {
        test1();
    }
}
