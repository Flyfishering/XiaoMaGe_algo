package 设计;

/**
 * https://leetcode-cn.com/problems/design-tic-tac-toe/
 */
public class _348_设计井字棋 {
    // https://leetcode-cn.com/problems/design-tic-tac-toe/solution/fei-zi-jie-ti-ku-348-zhong-deng-pan-ding-jtzc/
    class TicTacToe {
        int n;
        int[][] rows;
        int[][] cols;
        int[][] diagonals;
        /** Initialize your data structure here. */
        public TicTacToe(int n) {
            // 索引0无用，1表示玩家1，2表示玩家2，n表示对应的第n-1行或列
            rows = new int[3][n];
            cols = new int[3][n];
            // 2表示正对角线与反对角线
            diagonals = new int[3][2];
            this.n = n;
        }

        /** Player {player} makes a move at ({row}, {col}).
         @param row The row of the board.
         @param col The column of the board.
         @param player The player, can be either 1 or 2.
         @return The current winning condition, can be either:
         0: No one wins.
         1: Player 1 wins.
         2: Player 2 wins. */
        public int move(int row, int col, int player) {
            if (++rows[player][row] == n) {
                return player;
            }
            if (++cols[player][col] == n) {
                return player;
            }
            if (row == col && ++diagonals[player][0] == n) {
                return player;
            }
            if ((row + col == n - 1) && ++diagonals[player][1] == n) {
                return player;
            }
            return 0;
        }
    }


    // https://leetcode-cn.com/problems/design-tic-tac-toe/solution/java-o1shi-jian-onkong-jian-moveyi-xing-dai-ma-by-/
    // 还可以这么优化。。思路其实是一样的。
    // 上一个解，玩家1和2下一步棋都算1分
    // 这个解答，玩家1，1分，玩家2，-1分
    class TicTacToe2 {
        int[] rows;
        int[] cols;
        int n;
        int[] diagonals;
        /** Initialize your data structure here. */
        public TicTacToe2(int n) {
            rows = new int[n];
            cols = new int[n];
            diagonals = new int[2];
            this.n = n;
        }

        /** Player {player} makes a move at ({row}, {col}).
         @param row The row of the board.
         @param col The column of the board.
         @param player The player, can be either 1 or 2.
         @return The current winning condition, can be either:
         0: No one wins.
         1: Player 1 wins.
         2: Player 2 wins. */
        public int move(int row, int col, int player) {
            if (player == 1) {
                if (++rows[row] == n) {
                    return 1;
                }
                if (++cols[col] == n) {
                    return 1;
                }
                if (row == col && ++diagonals[0] == n) {
                    return 1;
                }
                if ((row + col == n - 1) && ++diagonals[1] == n) {
                    return 1;
                }
            } else {
                if (--rows[row] == -n) {
                    return 2;
                }
                if (--cols[col] == -n) {
                    return 2;
                }
                if (row == col && --diagonals[0] == -n) {
                    return 2;
                }
                if ((row + col == n - 1) && --diagonals[1] == -n) {
                    return 2;
                }
            }
            return 0;
        }
    }
}
