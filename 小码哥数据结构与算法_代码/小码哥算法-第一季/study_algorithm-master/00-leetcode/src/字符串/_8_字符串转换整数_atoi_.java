package 字符串;

/**
 * https://leetcode-cn.com/problems/string-to-integer-atoi/
 */
public class _8_字符串转换整数_atoi_ {
    public int myAtoi(String s) {
        char[] char1 = s.toCharArray();
        int len = char1.length;
        int idx = 0;
        boolean negative = false;
        int res = 0;

        // 去掉前导空格
        while (idx < len && char1[idx] == ' ') {
            idx++;
        }
        // 若全为空格，返回空
        if (idx == len) return 0;

        // 判断正负
        if (char1[idx] == '-') {
            negative = true;
            idx++;
        } else if (char1[idx] == '+') {
            idx++;
        }

        // 开始转化数字
        while (idx < len && Character.isDigit(char1[idx])) {
            int last = res;
            int digit = char1[idx] - '0';
            res = res * 10 + digit;
            if ((res / 10) != last) {
                return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            idx++;
        }

        return negative ? - res : res;
    }

    public static void main(String[] args) {
        _8_字符串转换整数_atoi_ test = new _8_字符串转换整数_atoi_();
        test.myAtoi("42");
    }
}
