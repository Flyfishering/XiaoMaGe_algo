package 二分;

/**
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/
 */
public class _153_寻找旋转排序数组中的最小值 {
    // 二分法好难理解啊！！
    // https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/solution/er-fen-cha-zhao-wei-shi-yao-zuo-you-bu-dui-cheng-z/
    public int findMin(int[] nums) {
        int li = 0, ri = nums.length - 1;
        while (li < ri) {
            int mid = (li + ri) >> 1;
            // 中值小于右值，收缩右边，往左边找
            if (nums[mid] < nums[ri]) {
                ri = mid;
            } else if (nums[mid] > nums[ri]){
                // 中值大于右值，收缩左边，往右边找
                li = mid + 1;
            }
        }
        return nums[li];
    }

    public static void main(String[] args) {
        _153_寻找旋转排序数组中的最小值 test = new _153_寻找旋转排序数组中的最小值();
        System.out.println(test.findMin(new int[] {2, 1}));
    }
}
