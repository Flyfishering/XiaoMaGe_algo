/**
 * https://leetcode-cn.com/problems/monotonic-array/
 */
public class _896_单调数列 {
    public boolean isMonotonic(int[] A) {
        boolean asc = true;
        boolean desc = true;

        for (int i = 1; i < A.length; i++) {
            if (A[i] < A[i - 1]) {
                asc = false;
            } else if (A[i] > A[i - 1]) {
                desc = false;
            }
            if (!(asc || desc)) break;
        }

        return asc || desc;
    }
}
