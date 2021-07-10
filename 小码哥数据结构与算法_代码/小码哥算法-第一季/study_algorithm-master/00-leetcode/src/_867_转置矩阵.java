/**
 * https://leetcode-cn.com/problems/transpose-matrix/
 */
public class _867_转置矩阵 {

    public int[][] transpose(int[][] matrix) {
        int[][] newMatrix = new int[matrix[0].length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                newMatrix[j][i] = matrix[i][j];
            }
        }

        return newMatrix;

    }
}
