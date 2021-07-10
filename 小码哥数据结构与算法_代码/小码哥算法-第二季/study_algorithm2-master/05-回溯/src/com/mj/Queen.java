package com.mj;

public class Queen {

    public static void main(String args[]) {
        new Queen().placeQueens(8);
    }

    // 下标表示放置皇后的行号，值表示列
    int[] cols;
    // 一共有多少种摆法
    int ways;

    /**
     * n皇后
     */
    void placeQueens(int n) {
        if (n < 1) return;
        // n皇后，就有n行
        cols = new int[n];
        place(0);
        System.out.println(n + "皇后一共有" + ways + "种摆法");
    }

    /**
     * 从第row行开始摆放皇后
     */
    void place(int row) {
        if (row == cols.length) {
            ways++;
            show();
            return;
        }
        for (int col = 0; col < cols.length; col++) {
            if (isValid(row, col)) {
                // 在第row行第col列摆放皇后
                cols[row] = col;
                place(row + 1);

                // 这边其实就是回溯了
            }
        }
    }

    /**
     * 判断第row行，第col列是否可以摆放皇后(剪枝处理)
     */
    boolean isValid(int row, int col) {
        for (int i = 0; i < row; i++) {
            // 第col列已经有皇后
            if (cols[i] == col) return false;
            // 斜线上已经有皇后
            if (row - i == Math.abs(col - cols[i])) return false;
        }
        return true;
    }

    void show() {
        for (int row = 0; row < cols.length; row++) {
            for (int col = 0; col < cols.length; col++) {
                if (cols[row] == col) {
                    System.out.print(1 + " ");
                } else {
                    System.out.print(0 + " ");
                }
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
}
