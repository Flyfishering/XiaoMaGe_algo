package 数组;

/**
 * https://leetcode-cn.com/problems/set-matrix-zeroes/
 */
public class _73_矩阵置零 {
    // 把第0行和第0列作为标记该行，该列是否需要置0的标志位
    // 再用两个变量来标记第0行，第0列是否需要置0
    public void setZeroes(int[][] matrix) {
        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        boolean col0 = false;
        boolean row0 = false;

        // 第0列的某个值为0的话，表明第0列需要变成0
        for (int row = 0; row < rowLen; row++) {
            if (matrix[row][0] == 0) {
                col0 = true;
                break;
            }
        }

        // 第0行的某个值为0的话，表明第0行需要变成0
        for (int col = 0; col < colLen; col++) {
            if (matrix[0][col] == 0) {
                row0 = true;
                break;
            }
        }

        // 对数组进行遍历，找出需要置0的行和列
        for (int row = 1; row < rowLen; row++) {
            for (int col = 1; col < colLen; col++) {
                if (matrix[row][col] == 0) {
                    matrix[row][0] = matrix[0][col] = 0;
                }
            }
        }

        // 开始置0处理
        for (int row = 1; row < rowLen; row++) {
            for (int col = 1; col < colLen; col++) {
                if (matrix[row][0] == 0 || matrix[0][col] == 0) {
                    matrix[row][col] = 0;
                }
            }
        }
        if (row0) {
            for (int col = 0; col < colLen; col++) {
                matrix[0][col] = 0;
            }
        }
        if (col0) {
            for (int row = 0; row < rowLen; row++) {
                matrix[row][0] = 0;
            }
        }
    }

    // 优化，只用一个额外变量来标记第0列是否需要置0
    public void setZeroes2(int[][] matrix) {
        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        boolean col0 = false;

        // 第0列用来记录每行是否需要置0
        // 第0行用来记录每列是否需要置0
        // 第0列是否需要置0，通过col0判断
        // 第0行是否需要置0，通过matrix[0][0]判断
        for (int row = 0; row < rowLen; row++) {
            // 若第0列有字符为0，说明第0列需要置0
            if (matrix[row][0] == 0) col0 = true;
            for (int col = 1; col < colLen; col++) {
                if (matrix[row][col] == 0) {
                    matrix[0][col] = 0;
                    matrix[row][0] = 0;
                }
            }
        }
        // 开始置0处理
        // 从下往上扫描，是因为若matrix[0][0]为0，会把第0行所有内容都置为0，影响后续的处理
        for (int row = rowLen - 1; row >= 0; row--) {
            for (int col = colLen - 1; col > 0; col--) {
                if (matrix[row][0] == 0 || matrix[0][col] == 0) {
                    matrix[row][col] = 0;
                }
            }
            if (col0) matrix[row][0] = 0;
        }
    }
}
