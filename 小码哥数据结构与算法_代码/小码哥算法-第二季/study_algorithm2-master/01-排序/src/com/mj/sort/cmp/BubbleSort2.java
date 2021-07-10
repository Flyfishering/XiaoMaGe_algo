package com.mj.sort.cmp;

import com.mj.sort.Sort;

// 考虑了途中数据已经排序完成后的冒泡
public class BubbleSort2<E extends Comparable<E>> extends Sort<E> {

    @Override
    protected void sort() {
        for (int end = array.length - 1; end > 0; end--) {
            boolean sorted = true;
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(begin, begin - 1) < 0) {
                    swap(begin, begin - 1);
                    sorted = false;
                }
            }
            if (sorted) break;
        }
    }

}
