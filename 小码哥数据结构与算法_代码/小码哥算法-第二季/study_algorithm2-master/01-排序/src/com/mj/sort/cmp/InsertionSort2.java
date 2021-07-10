package com.mj.sort.cmp;

import com.mj.sort.Sort;

public class InsertionSort2<E extends Comparable<E>> extends Sort<E> {
    @Override
    protected void sort() {
        for (int begin = 1; begin < array.length; begin++) {
            int cur = begin;
            E v = array[cur];
            while (cur > 0 && cmp(v, array[cur-1]) < 0) { // 注意这个<，若变成<=，就是不稳定的排序了
                array[cur] = array[--cur];  // 由每次都交换优化为了挪动
            }
            array[cur] = v;
        }
    }
}
