package DFS;

/**
 * https://leetcode-cn.com/problems/number-of-islands/
 */
public class _200_岛屿数量 {
    // https://leetcode-cn.com/problems/number-of-islands/solution/dao-yu-lei-wen-ti-de-tong-yong-jie-fa-dfs-bian-li-/
    public int numIslands(char[][] grid) {
        // 岛屿数量
        int ans = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                // 发现岛屿，则对该岛深度遍历
                if (grid[row][col] == '1') {
                    dfsGrid(grid, row, col);
                    ans++;
                }
            }
        }
        return ans;
    }

    // 深度搜索
    private void dfsGrid(char[][] grid, int row, int col) {
        // 下标若越界，则返回
        if (!inArea(grid, row, col)) {
            return;
        }
        // 若要遍历的格子已经不是岛屿，则返回
        if (grid[row][col] != '1') return;
        // 标记遍历过的格子
        grid[row][col] = '2';
        // 深度遍历
        // 上
        dfsGrid(grid, row - 1, col);
        // 下
        dfsGrid(grid, row + 1, col);
        // 左
        dfsGrid(grid, row, col - 1);
        // 右
        dfsGrid(grid, row, col + 1);
    }

    // 判断网格下标是否越界
    private boolean inArea(char[][] grid, int row, int col) {
        return 0 <= row && row < grid.length
                && 0 <= col && col < grid[0].length;
    }
}
