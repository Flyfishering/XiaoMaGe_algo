package DFS;

/**
 * https://leetcode-cn.com/problems/word-search/
 */
public class _79_单词搜索 {
    public boolean exist(char[][] board, String word) {
        char[] cw = word.toCharArray();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == cw[0]) {
                    if (dfs(board, i, j, 0, cw)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int i, int j, int idx, char[] cw) {
        if (idx == cw.length) return true;
        if (!inArea(board, i, j) || board[i][j] == '#'
                || board[i][j] != cw[idx]) return false;
        char bak = board[i][j];
        board[i][j] = '#';
        boolean res =  dfs(board, i - 1, j, idx + 1, cw)
                        || dfs(board, i + 1, j, idx + 1, cw)
                        || dfs(board, i, j - 1, idx + 1, cw)
                        || dfs(board, i, j + 1, idx + 1, cw);
        board[i][j] = bak;
        return res;
    }

    private boolean inArea(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }
        return true;
    }
}
