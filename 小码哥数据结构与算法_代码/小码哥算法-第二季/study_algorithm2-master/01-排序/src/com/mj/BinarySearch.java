package com.mj;

// 二分查找
public class BinarySearch {
    // 查找v在有序数组中的位置
    public static int indexOf(int[] array, int v) {
        if (array == null || array.length == 0) return -1;
        int begin = 0;
        int end = array.length; // 设计区间，尽量左闭右开。[begin, end)。好处是算长度，直接end - begin
        while (begin < end) { // 循环的条件为，至少要有一个元素
            int mid = (begin + end) >> 1;
            if (v < array[mid]) {
                end = mid;
            } else if (v > array[mid]) {
                begin = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    // 查找v在有序数组中的待插入位置
    public static int search(int[] array, int v) {
        if (array == null || array.length == 0) return -1;
        int begin = 0;
        int end = array.length;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (v < array[mid]) {
                end = mid;
            } else {
                begin = mid + 1;
            }
        }
        return begin;
    }
}
