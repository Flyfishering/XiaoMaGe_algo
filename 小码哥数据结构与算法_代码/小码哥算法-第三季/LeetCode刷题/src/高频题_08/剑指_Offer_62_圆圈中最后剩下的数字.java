package 高频题;

public class 剑指_Offer_62_圆圈中最后剩下的数字 {
    // 递归
    public int lastRemaining(int n, int m) {
        return n == 1 ? 0 : (lastRemaining(n - 1, m) + m) % n;
    }

    // 迭代
    // f(1, 3) = 0
    // f(2, 3) = (f(1, 3) + 3) % 2
    // ...
    // f(8, 3) = (f(7, 3) + 3) % 8
    // f(9, 3) = (f(8, 3) + 3) % 9
    // f(10, 3) = (f(9, 3) + 3) % 10
    public int lastRemaining1(int n, int m) {
        int res = 0;
        for (int i = 2; i <= n; i++) {
            res = (res + m) % i;
        }

        return res;
    }
}
