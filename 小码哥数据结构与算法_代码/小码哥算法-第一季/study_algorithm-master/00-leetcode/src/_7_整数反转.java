/**
 * https://leetcode-cn.com/problems/reverse-integer/
 */
public class _7_整数反转 {
    public int reverse(int x) {
        int res = 0;

        while (x != 0) {
            int last = res;
            res = res * 10 + x % 10;
            if ((res / 10) != last) return 0;
            x = x / 10;
        }

        return res;
    }
}
