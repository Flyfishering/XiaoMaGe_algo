package 高频题;

/**
 * https://leetcode-cn.com/problems/reverse-integer/
 */
public class _7_整数反转 {
    public int reverse(int x) {
        int res = 0;

        while (x != 0) {
            int prevRes = res;
            res = res * 10 + x % 10;
            if (res / 10 != prevRes) return 0;
            x /= 10;
        }

        return res;
    }
}
