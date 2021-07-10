package 高频题_08;

/*
* https://leetcode-cn.com/problems/powx-n/
* */
public class _50_Pow_x_n_ {
    /*
     * 解法1: 迭代实现
     * */
    public double myPow(double x, int n) {
        long y = (n < 0) ? -((long) n) : n;
        double res = 1.0;
        while (y > 0) {
            if ((y & 1) == 1) {
                res *= x; // 如果最后一个二进制位是1，就累乘上x
            }
            x *= x;
            y = y >> 1;  // 舍弃掉最后一个二进制位
        }
        return (n < 0) ? (1 / res) : res;
    }

    /*
     * 解法2: 递归实现
     * */
    public double myPow2(double x, int n) {
        if (n == 0) return 1;
        if (n == -1) return 1 / x;
        double half = myPow2(x, n >> 1);
        half *= half;
        // 是否为奇数
        return  ((n & 1) == 1) ? (half * x) : half;
    }
}