package 回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/n-queens/
 */
public class _51_N_皇后 {
    List<List<String>> list = new ArrayList<>();
    int[] cols;
    int ways;

    public List<List<String>> solveNQueens(int n) {
        cols = new int[n];
        place(0);
        return list;
    }

    private void place(int row) {
        if (row == cols.length) {
            ways++;
            add();
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

    private void add() {
        List<String> qList = new ArrayList<>();
        for (int row = 0; row < cols.length; row++) {
            StringBuilder sb = new StringBuilder();
            for (int col = 0; col < cols.length; col++) {
                if (col == cols[row]) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            qList.add(sb.toString());
        }
        list.add(qList);
    }
}
