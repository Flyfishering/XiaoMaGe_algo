package 数组排序_01;

/*
给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
说明:

    初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
    你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
示例:

输入:
    nums1 = [1,2,3,0,0,0], m = 3
    nums2 = [2,5,6],       n = 3

输出: [1,2,2,3,5,6]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/merge-sorted-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


*/
public class _01_88_合并两个有序数组 {
    /*
    * 思路:
    * 先定义两个index指向数组元素有效数组的最后,然后分别的往前依次比较
    * 既然是有序数组,就从后往前遍历,这样处理合并会更方便 注意nums1的下标越界情况
    * */
    static public void merge(int[] nums1, int m, int[] nums2, int n) {
        int firstInx = m - 1;
        int secondIndx = n - 1;
        int lastIdx = nums1.length - 1;
        while (secondIndx >= 0) {
            if (firstInx >= 0 && nums1[firstInx] > nums2[secondIndx]) {
                nums1[lastIdx--] = nums1[firstInx--];
            } else {
                nums1[lastIdx--] = nums2[secondIndx--];
            }
        }
    }

    public static void main(String[] args) {
        int[] num1 = new int[]{1,2,3,0,0,0};
        int[] num2 = new int[]{2,5,6};
        merge(num1,3,num2,3);
    }
}
