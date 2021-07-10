package 动态规划_05;

/*
* https://leetcode-cn.com/problems/li-wu-de-zui-da-jie-zhi-lcof/
* */
public class _剑指_Offer_47_礼物的最大价值 {
    public int maxValue(int[][] grid) {
        int rows = grid.length;    // 行
        int cols = grid[0].length; // 列

        int[][] res = new int[rows][cols];
        res[0][0] = grid[0][0];
        for (int i = 1; i < rows; i++) {
            res[i][0] = res[i-1][0] + grid[i][0];
        }
        for (int i = 1; i < cols; i++) {
            res[0][i] = res[0][i-1] + grid[0][i];
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                res[i][j] = Math.max(res[i][j-1],res[i-1][j]) + grid[i][j];
            }
        }
        return res[rows-1][cols-1];
    }

    public static void main(String[] args) {
        _剑指_Offer_47_礼物的最大价值 o = new _剑指_Offer_47_礼物的最大价值();
        int[][] grid = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        o.maxValue(grid);
    }
}
