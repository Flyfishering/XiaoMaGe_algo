package 数组;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/spiral-matrix-ii/
 */
public class _59_螺旋矩阵_II {
    public int[][] generateMatrix(int n) {
        int top = 0;
        int down = n - 1;
        int left = 0;
        int right = n - 1;

        int[][] ans = new int[n][n];
        int val = 1;

        while (true) {
            // 从左到右
            for (int i = left; i <= right; i++) {
                ans[top][i] = val++;
            }
            if (++top > down) break;
            // 从上到下
            for (int i = top; i <= down; i++) {
                ans[i][right] = val++;
            }
            if (--right < left) break;
            // 从右到左
            for (int i = right; i >= left; i--) {
                ans[down][i] = val++;
            }
            if (--down < top) break;
            // 从下到上
            for (int i = down; i >= top; i--) {
                ans[i][left] = val++;
            }
            if (++left > right) break;
        }
        return ans;
    }

    public static void main(String[] args) {
        _59_螺旋矩阵_II test = new _59_螺旋矩阵_II();
        int[][] ans = test.generateMatrix(3);
        for (int[] an : ans) {
            System.out.println(Arrays.toString(an));
        }
    }
}
