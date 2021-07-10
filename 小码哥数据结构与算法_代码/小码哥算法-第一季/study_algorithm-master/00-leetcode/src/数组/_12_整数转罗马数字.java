package 数组;

/**
 * https://leetcode-cn.com/problems/integer-to-roman/
 */
public class _12_整数转罗马数字 {
    // 贪心
    public String intToRoman(int num) {
        int[] value = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
        String[] symbols = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            for (int i = value.length - 1; i >= 0; i--) {
                if (num >= value[i]) {
                    sb.append(symbols[i]);
                    num -= value[i];
                    break;
                }
            }
        }
        return sb.toString();
    }
}
