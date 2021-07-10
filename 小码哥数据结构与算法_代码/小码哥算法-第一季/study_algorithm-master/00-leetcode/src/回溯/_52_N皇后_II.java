package 回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/n-queens-ii/
 */
public class _52_N皇后_II {
    int[] cols;
    int ways;

    public int totalNQueens(int n) {
        cols = new int[n];
        place(0);
        return ways;
    }

    private void place(int row) {
        if (row == cols.length) {
            ways++;
            return;
        }
        for (int col = 0; col < cols.length; col++) {
            if (isValid(row, col)) {
                cols[row] = col;
                place(row + 1);
            }
        }
    }

    private boolean isValid(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (cols[i] == col) return false;
            if (row - i == Math.abs(col - cols[i])) return false;
        }
        return true;
    }

}
