package 字符串;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/zigzag-conversion/
 * https://leetcode-cn.com/problems/zigzag-conversion/solution/zzi-xing-bian-huan-by-jyd/
 */
public class _6_Z_字形变换 {
    public String convert(String s, int numRows) {
        if (numRows < 2) return s;
        char[] cs = s.toCharArray();
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }

        int idx = 0, increment = 1;
        for (int i = 0; i < cs.length; i++) {
            rows.get(idx).append(cs[i]);
            if (idx == numRows - 1) {
                increment = -1;
            } else if (idx == 0){
                increment = 1;
            }
            idx += increment;
        }
        StringBuilder ans = new StringBuilder();
        for (StringBuilder sb : rows) {
            ans.append(sb);
        }
        return ans.toString();
    }
}
