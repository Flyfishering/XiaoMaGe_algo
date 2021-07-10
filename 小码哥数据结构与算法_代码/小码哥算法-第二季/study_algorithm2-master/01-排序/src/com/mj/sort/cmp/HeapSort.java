package com.mj.sort.cmp;

import com.mj.sort.Sort;

// 堆排序。堆排序其实是对选择排序的一种优化
public class HeapSort<E extends Comparable<E>> extends Sort<E> {
    private int heapSize;

    @Override
    protected void sort() {
        heapSize = array.length;
        // 原地建堆
        for (int i = (heapSize >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }
        while (heapSize > 1) { // 堆只有一个元素的时候无需继续循环
            // 交换堆顶和尾部元素，堆size-1
            swap(0, --heapSize);
            // 对0位置进行下滤(恢复堆的性质)
            siftDown(0);
        }

    }

    // 下滤
    private void siftDown(int index) {
        E element = array[index];
        int half = heapSize >> 1;
        // 第一个叶子节点的索引 == 非叶子节点的数量
        while (index < half) { // 必须保证index位置的节点是非叶子节点(叶子节点无需也无法下滤)
            // index的节点有两种情况
            // 1.只有左子节点
            // 2.同时有左右子节点

            // 默认为左子节点,跟左子节点进行比较
            int childIndex = (index << 1) + 1;
            E child = array[childIndex];

            // 右子节点
            int rightIndex = childIndex + 1;
            // 选出左右子节点中最大的那个
            if (rightIndex < heapSize && cmp(array[rightIndex], child) > 0) {
                child = array[childIndex = rightIndex];
            }

            if (cmp(element, child) >= 0) break;

            // 将子节点存放到index位置
            array[index] = child;
            index = childIndex;

        }
        array[index] = element;
    }
}
