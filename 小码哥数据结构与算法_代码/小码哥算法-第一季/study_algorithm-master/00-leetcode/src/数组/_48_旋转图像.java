package 数组;

/**
 * https://leetcode-cn.com/problems/rotate-image/
 */
public class _48_旋转图像 {
    // 先转置(翻转)再对称
    public void rotate(int[][] matrix) {
        // 转置(翻转)，注意col起始条件的加一。没有加一也能过，但是是不必要的交换
        // 以对角线（左上-右下）为轴进行翻转
        for (int row = 0; row < matrix.length; row++) {
            for (int col = row + 1; col < matrix[0].length; col++) {
                int tmp = matrix[row][col];
                matrix[row][col] = matrix[col][row];
                matrix[col][row] = tmp;
            }
        }

        // 翻转的另一种写法。两种写法都应该掌握！
        // 两种写法只是col的开始与结束不同！
//        for (int row = 0; row < matrix.length; row++) {
//            for (int col = 0; col < row; col++) {
//                int tmp = matrix[row][col];
//                matrix[row][col] = matrix[col][row];
//                matrix[col][row] = tmp;
//            }
//        }

        // 对称
        for (int[] ints : matrix) {
            int li = 0, ri = matrix[0].length - 1;
            while (li < ri) {
                int tmp = ints[li];
                ints[li] = ints[ri];
                ints[ri] = tmp;
                li++;
                ri--;
            }
        }
    }
}
