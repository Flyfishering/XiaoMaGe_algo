package 字符串;

/**
 * https://leetcode-cn.com/problems/multiply-strings/
 * https://leetcode-cn.com/problems/multiply-strings/solution/you-hua-ban-shu-shi-da-bai-994-by-breezean/
 */
public class _43_字符串相乘 {
    // 时间O(mn),空间O(m+n)
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";
        char[] cs1 = num1.toCharArray();
        char[] cs2 = num2.toCharArray();

        int[] res = new int[cs1.length + cs2.length];

        for (int i = cs1.length - 1; i >= 0; i--) {
            for (int j = cs2.length - 1; j >= 0; j--) {
                int sum = res[i + j + 1] + (cs1[i] - '0') * (cs2[j] - '0');
                // 计算低位
                res[i + j + 1] = sum % 10;
                // 高位可能产生进位，进位会在下一轮循环的低位去处理
                res[i + j] += sum / 10;
            }
        }
        return numToString(res);
    }

    private String numToString(int[] res) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            // 若最高位为0，则略过
            if (i == 0 && res[i] == 0) continue;
            sb.append(res[i]);
        }
        return sb.toString();
    }
}
