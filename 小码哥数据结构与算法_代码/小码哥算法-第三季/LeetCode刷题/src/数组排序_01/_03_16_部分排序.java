package 数组排序_01;

/*
给定一个整数数组，编写一个函数，找出索引m和n，只要将索引区间[m,n]的元素排好序，整个数组就是有序的。
注意：n-m尽量最小，也就是说，找出符合条件的最短序列。函数返回值为[m,n]，若不存在这样的m和n（例如整个数组是有序的），请返回[-1,-1]。

示例：

输入： [1,2,4,7,10,11,7,12,6,7,16,18,19]
输出： [3,9]
提示：

0 <= len(array) <= 1000000

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/sub-sort-lcci
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

// 从左往右遍历 找 右边的范围,正常情况,从左往右依次增大,发现有逆序的,就记录其位置(不要疑惑了,又不是发现第一个逆序对就不往后遍历了)
// 从右往左遍历 找 左边的范围,正常情况,从右往左依次减小,发现有逆序的,就记录其位置
*/
public class _03_16_部分排序 {
    static public int[] subSort(int[] array) {
        if (array.length == 0) return new int[]{-1,-1};
        int max = array[0];
        int r = -1;
        for (int i = 1; i < array.length; i++) {
            if (array[i] >= max) { // 从左往右遍历,就是该依次变大,不断的更新最大值
                max = array[i];
            } else { // 发现有比最大值还小的,说明出现逆序对,记录其位置
                r = i;
            }
        }
        if (r == -1) return new int[]{-1,-1};
        int min = array[array.length - 1];
        int l = -1;
        for (int i = array.length - 2; i >= 0 ; i--) {
            if (array[i] <= min) { // 从右往左遍历,就是该依次变小,不断的更新最小值
                min = array[i];
            } else { // 发现有比最小值还大的,说明出现逆序对,记录其位置
                l = i;
            }
        }
        return new int[]{l,r};
    }

    public static void main(String[] args) {
        int[] array = subSort(new int[]{1,2,4,7,10,11,7,12,6,7,16,18,19});
        for (int i = 0; i < array.length; i++) {
            System.out.println(i);
        }
    }
}
