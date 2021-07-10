package 数组;

import java.util.ArrayList;
import java.util.List;

public class _54_螺旋矩阵 {
    public List<Integer> spiralOrder(int[][] matrix) {
        int top = 0;
        int down = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        List<Integer> ans = new ArrayList<>();

        while (true) {
            // 从左到右
            for (int i = left; i <= right; i++) {
                ans.add(matrix[top][i]);
            }
            if (top++ == down) break;
            // 从上到下
            for (int i = top; i <= down; i++) {
                ans.add(matrix[i][right]);
            }
            if (left == right--) break;
            // 从右到左
            for (int i = right; i >= left; i--) {
                ans.add(matrix[down][i]);
            }
            if (top == down--) break;
            // 从下到上
            for (int i = down; i >= top; i--) {
                ans.add(matrix[i][left]);
            }
            if (left++ == right) break;
        }
        return ans;
    }
}
