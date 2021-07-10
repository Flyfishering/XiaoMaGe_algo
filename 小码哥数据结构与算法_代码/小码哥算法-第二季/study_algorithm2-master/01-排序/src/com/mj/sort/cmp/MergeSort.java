package com.mj.sort.cmp;

import com.mj.sort.Sort;

@SuppressWarnings("unchecked")
public class MergeSort<E extends Comparable<E>> extends Sort<E> {
    private E[] leftArray;

    @Override
    protected void sort() {
        leftArray = (E[])new Comparable[array.length >> 1];
        sort(0, array.length);
    }

    // 对[begin, end)范围内的数据进行归并排序
    private void sort(int begin, int end) {
        if ((end - begin) < 2) return; // 左闭右开，end - begin为区间内的元素数量
        int mid = (begin + end) >> 1;
        sort(begin, mid);
        sort(mid, end);
        merge(begin, mid, end);
    }

    // 将[begin, mid)和[mid, end)范围的序列合并成一个有序序列
    private void merge(int begin, int mid, int end) {
        int li = 0, le = mid - begin;
        int ri = mid, re = end;
        int ai = begin;

        // 备份左边数组
        for (int i = li; i < le; i++) {
            leftArray[i] = array[begin + i];
        }
        while (li < le) { // 左边还没结束的话进行循环。左边数组结束了的话，排序其实就结束了
//            if (cmp(leftArray[li], array[ri]) <= 0) { // =，表示优先使用左边的。没有=的话，算法会变成不稳定
//                array[ai++] = leftArray[li++];
//            } else {
//                array[ai++] = array[ri++];
//            }
            // ri < re，while循环的条件是li < le，所以在这边要防止右边的数组下标越界
            if (ri < re && cmp(array[ri], leftArray[li]) < 0) { // 只有右边小的时候，才用右边。相等的时候，用左边，这样算法才稳定
                array[ai++] = array[ri++];
            } else {
                array[ai++] = leftArray[li++];
            } // cmp部分若加上了=，则会失去稳定性
        }
    }
}
