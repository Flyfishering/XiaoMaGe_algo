package com.mj.sort.cmp;

import com.mj.sort.Sort;

// 终极版冒泡。。优化了末尾局部有序的情况
public class BubbleSort3<E extends Comparable<E>> extends Sort<E> {

    @Override
    protected void sort() {
        for (int end = array.length - 1; end > 0; end--) {
            // sortedIndex的初始值在数组完全有序的时候有用
            int sortedIndex = 1;
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(begin, begin - 1) < 0) { // 注意这边的<，如果用的是<=，那么会变成不稳定算法
                    swap(begin, begin - 1);
                    sortedIndex = begin;
                }
            }
            end = sortedIndex;
        }
    }

}
