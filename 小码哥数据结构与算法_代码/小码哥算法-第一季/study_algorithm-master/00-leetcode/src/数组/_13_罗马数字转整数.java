package 数组;

/**
 * https://leetcode-cn.com/problems/roman-to-integer/
 */
public class _13_罗马数字转整数 {
    public int romanToInt(String s) {
        char[] cs = s.toCharArray();
        int ans = 0;
        for (int i = 0; i < cs.length; i++) {
            int val = getValue(cs[i]);
            int next = i < cs.length - 1 ? getValue(cs[i + 1]) : 0;
            ans += val < next ? - val : val;
        }
        return ans;
    }

    private int getValue(char c) {
        switch (c) {
            case 'I' : return 1;
            case 'V' : return 5;
            case 'X' : return 10;
            case 'L' : return 50;
            case 'C' : return 100;
            case 'D' : return 500;
            case 'M' : return 1000;
        }
        return -1;
    }
}
