package com.mj.sort.cmp;

import com.mj.sort.Sort;

public class SelectionSort<E extends Comparable<E>> extends Sort<E> {
    @Override
    protected void sort() {
        for (int end = array.length - 1; end > 0; end--) {
            int maxIndex = 0;
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(maxIndex, begin) <= 0) { // 注意<=，如果没有=，会变成不稳定算法
                    maxIndex = begin;
                }
            }
            swap(maxIndex, end);
        }
    }
}
