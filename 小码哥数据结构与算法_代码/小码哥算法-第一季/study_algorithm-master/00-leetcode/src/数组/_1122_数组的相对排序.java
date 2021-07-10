package 数组;

import java.util.TreeMap;

/**
 * https://leetcode-cn.com/problems/relative-sort-array/
 */
public class _1122_数组的相对排序 {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int index = 0;
        int temp;

        for (int i = 0; i < arr2.length; i++) {
            for (int j = index; j < arr1.length; j++) {
                if (arr1[j] == arr2[i]) {
                    temp = arr1[index];
                    arr1[index] = arr2[i];
                    arr1[j] = temp;
                    index++;
                }
            }
        }

        for (int i = index; i < arr1.length; i++) {
            for (int j = i + 1; j < arr1.length; j++) {
                if (arr1[j] < arr1[i]) {
                    temp = arr1[i];
                    arr1[i] = arr1[j];
                    arr1[j] = temp;
                }
            }
        }
        return arr1;
    }
}
