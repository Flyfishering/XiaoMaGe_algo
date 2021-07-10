package com.mj.sort;

public class RadixSort extends Sort<Integer> {
    @Override
    protected void sort() {
        // 找出最大值
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        // 个位数: array[i] / 1 % 10 = 3
        // 十位数：array[i] / 10 % 10 = 9
        // 百位数：array[i] / 100 % 10 = 5
        for (int divider = 1; divider <= max; divider *= 10) {
            countingSort(divider);
        }

    }

    // 利用计数排序
    protected void countingSort(int divider) {
        // 每次对基数的排序，肯定是0~9的范围
        int[] counts = new int[10];
        for (int i = 0; i < array.length; i++) {
            // 统计每个整数出现的次数
            counts[array[i] / divider % 10]++;
        }
        // 累加次数
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }

        // 从后往前遍历元素，将它放到有序数组中的合适位置(之所以从后往前遍历，是为了保证稳定性)
        int[] newArray = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            newArray[--counts[array[i] / divider % 10]] = array[i];
        }

        // 将有序数组覆盖回去
        for (int i = 0; i < array.length; i++) {
            array[i] = newArray[i];
        }
    }
}
