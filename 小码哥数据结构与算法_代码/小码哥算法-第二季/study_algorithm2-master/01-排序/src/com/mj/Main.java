package com.mj;

import com.mj.sort.*;
import com.mj.sort.cmp.*;
import com.mj.tool.Asserts;
import com.mj.tool.Integers;

import java.util.Arrays;

@SuppressWarnings({"rawtypes", "unchecked"})
public class Main {

    public static void main(String args[]) {
        Integer[] array = Integers.random(20000, 1, 100000);

        testSorts(array,
                new BubbleSort3(),
                new SelectionSort(),
                new HeapSort(),
                new InsertionSort1(),
                new InsertionSort2(),
                new InsertionSort3(),
                new MergeSort(),
                new QuickSort(),
                new ShellSort(),
                new CountingSort(),
                new RadixSort());
    }

    static void testSorts(Integer[] array, Sort... sorts) {
        for (Sort sort : sorts) {
            Integer[] newArray = Integers.copy(array);
            sort.sort(newArray);
            Asserts.test(Integers.isAscOrder(newArray));
        }
        Arrays.sort(sorts);
        for (Sort sort : sorts) {
            System.out.println(sort);
        }
    }

    // 最普通的冒泡
    static void bubbleSort1(Integer[] array) {
        for (int end = array.length - 1; end > 0; end--) {
            for (int begin = 1; begin <= end; begin++) {
                if (array[begin] < array[begin - 1]) {
                    int temp = array[begin - 1];
                    array[begin - 1] = array[begin];
                    array[begin] = temp;
                }
            }
        }
    }

    // 考虑了途中数据已经排序完成后的冒泡
    static void bubbleSort2(Integer[] array) {
        for (int end = array.length - 1; end > 0; end--) {
            boolean sorted = true;
            for (int begin = 1; begin <= end; begin++) {
                if (array[begin] < array[begin - 1]) {
                    int temp = array[begin - 1];
                    array[begin - 1] = array[begin];
                    array[begin] = temp;
                    sorted = false;
                }
            }
            if (sorted) break;
        }
    }

    // 终极版冒泡。。
    static void bubbleSort3(Integer[] array) {
        for (int end = array.length - 1; end > 0; end--) {
            // sortedIndex的初始值在数组完全有序的时候有用
            int sortedIndex = 1;
            for (int begin = 1; begin <= end; begin++) {
                if (array[begin] < array[begin - 1]) { // 注意这边的<，如果用的是<=，那么会变成不稳定算法
                    int temp = array[begin - 1];
                    array[begin - 1] = array[begin];
                    array[begin] = temp;
                    sortedIndex = begin;
                }
            }
            end = sortedIndex;
        }
    }

    // 选择排序
    static void selectionSort(Integer[] array) {
        for (int end = array.length - 1; end > 0; end--) {
            int maxIndex = 0;
            for (int begin = 1; begin <= end; begin++) {
                if (array[maxIndex] <= array[begin]) { // 注意<=，如果没有=，会变成不稳定算法
                    maxIndex = begin;
                }
            }
            int temp = array[end];
            array[end] = array[maxIndex];
            array[maxIndex] = temp;
        }
    }
}
