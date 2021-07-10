package 排序_数组;

/**
 * https://leetcode-cn.com/problems/sub-sort-lcci/
 */
public class _面试题_16_16_部分排序 {

    public int[] subSort(int[] array) {
        if (array.length == 0) return new int[] {-1, -1};
        // 从左扫描到右寻找逆序对(正序：逐渐变大)
        int max = array[0];
        // 用来记录最右边的那个逆序对的位置
        int r = -1;

        for (int i = 1; i < array.length; i++) {
            if (array[i] < max) { // 小于，发现逆序对，记录逆序对的位置
                r = i;
            } else {
                max = array[i];
            }
        }

        // 若r == -1，表示不存在逆序对，可以提前结束
        if (r == -1) return new int[] {-1, -1};

        // 从右扫描到做寻找逆序对(正序：逐渐变小)
        int min = array[array.length - 1];
        // 用来记录最左边的那个逆序对的位置
        int l = -1;

        for (int i = array.length - 2; i >= 0; i--) {
            if (array[i] > min) { // 大于，发现逆序对，记录逆序对的位置
                l = i;
            } else {
                min = array[i];
            }
        }
        return new int[] {l, r};
    }

    public int[] subSort_2(int[] array) {
        if (array.length == 0) return new int[] {-1, -1};
        // 从左扫描到右寻找逆序对(正序：逐渐变大)
        int i = 1;
        int r = -1;
        int l = -1;
        int max = array[0];
        int min = array[array.length - 1];
        while (i < array.length) {
            if (array[i] >= max) {
                max = array[i++];
            } else {
                r = i++;
            }
        }

        i = array.length - 2;
        while (i >= 0) {
            if (array[i] <= min) {
                min = array[i--];
            } else {
                l = i--;
            }
        }

        return new int[] {l, r};
    }
}
