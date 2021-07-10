package 数组;

/**
 * https://leetcode-cn.com/problems/score-after-flipping-matrix/
 */
public class _861_翻转矩阵后的得分 {
    public int matrixScore(int[][] A) {

        int[] count0 = new int[A[0].length];
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            int first = A[i][0];
            for (int j = 0; j < A[i].length; j++) {
                if (first == 0) {
                    A[i][j] ^= 1;
                }
                if (A[i][j] == 0) {
                    count0[j]++;
                }
            }
        }


        // TODO 可以优化
        // https://leetcode-cn.com/problems/score-after-flipping-matrix/solution/fan-zhuan-ju-zhen-tan-xin-xin-lu-li-chen-21h7/
        for (int j = 0; j < A[0].length; j++) {
            //
            if (count0[j] > (A.length / 2)) {
                for (int i = 0; i < A.length; i++) {
                    A[i][j] ^= 1;
                    if (A[i][j] == 1) {
                        sum += 1 << (A[0].length - j - 1);
                    }
                }
            } else {
                for (int i = 0; i < A.length; i++) {
                    if (A[i][j] == 1) {
                        sum += 1 << (A[0].length - j - 1);
                    }
                }
            }
        }

//        for (int i = 0; i < A.length; i++) {
//            for (int j = 0; j < A[i].length; j++) {
//                System.out.print(A[i][j] + " ");
//            }
//            System.out.println();
//        }

        return sum;
    }

}
