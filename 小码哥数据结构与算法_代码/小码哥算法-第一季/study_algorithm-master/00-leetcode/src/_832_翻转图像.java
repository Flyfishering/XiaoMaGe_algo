/**
 * https://leetcode-cn.com/problems/flipping-an-image/
 */
public class _832_翻转图像 {
    public int[][] flipAndInvertImage(int[][] A) {
        for (int[] nums : A) {
            // 利用双指针对每一行进行翻转
            int index = 0;
            int end = nums.length - 1;
            int temp;
            while (index < end) {
                temp = nums[index];
                // 利用异或进行图片反转(1 -> 0, 0 -> 1)。也可以用1-x
                nums[index++] = nums[end] ^ 1;
                nums[end--] = temp ^ 1;
            }
            if (index == end) {
                nums[index] ^= 1;
            }
        }

        return A;
    }
}
