package com.mj.sort.cmp;

import com.mj.sort.Sort;

// 利用二分搜索优化的插入排序
public class InsertionSort3<E extends Comparable<E>> extends Sort<E> {
    @Override
    protected void sort() {
        for (int begin = 1; begin < array.length; begin++) {
            int insertIndex = search(begin);
            E v = array[begin];
            // 将[insertIndex, begin)范围内的元素往右挪动一个单位
//            for (int i = begin - 1; i >= insertIndex; i--) {
//                array[i + 1] = array[i];
//            }
            for (int i = begin; i > insertIndex; i--) {
                array[i] = array[i - 1];
            }
            array[insertIndex] = v;
        }
    }

    // 利用二分搜索找到index位置元素的待插入位置
    // 已经排好序的有序数组的区间为[0, index)
    private int search(int index) {
        E v = array[index];
        int begin = 0;
        int end = index;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (cmp(v, array[mid]) < 0) {
                end = mid;
            } else {
                begin = mid + 1;
            }
        }
        return begin;
    }
}
