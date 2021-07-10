package 高频题;

/**
 * https://leetcode-cn.com/problems/powx-n/
 */
public class _50_Pow {
    // 递归
    public double myPow(double x, int n) {
        if (n == 0) return 1;
        if (n == -1) return 1 / x;
        // 是否为奇数
        boolean odd = (n & 1) == 1;
        double half = myPow(x, n >> 1);
        half *= half;
        // 是否为负数
        boolean neg = n < 0;
//        x = n < 0 ? (1 / x) : x;
        return odd ? (half * x) : half;
    }

    // 迭代
    public double myPow2(double x, int n) {
        double res = 1.0;
        boolean neg = n < 0;
        long y = neg ? (-(long) n) : n;

        while (y > 0) {
            if ((y & 1) == 1) {
                res *= x;
            }
            x *= x;
            y >>= 1;
        }
        return neg ? 1 / res : res;
    }

    public static void main(String[] args) {
        System.out.println(new _50_Pow().myPow2(2, 10));
    }

}
