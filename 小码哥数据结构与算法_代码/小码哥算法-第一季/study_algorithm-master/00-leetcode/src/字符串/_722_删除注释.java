package 字符串;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/remove-comments/
 */
public class _722_删除注释 {
    // https://leetcode-cn.com/problems/remove-comments/solution/shan-chu-zhu-shi-by-leetcode/
    public List<String> removeComments(String[] source) {
        boolean inBlock = false;
        List<String> ans = new ArrayList<>();
        StringBuilder newLine = null;

        for (String s : source) {
            char[] cs = s.toCharArray();
            // 注意这个地方。删除块注释的时候，有可能带来不同行的内容合并。这个时候，
            // 就不能new一个StringBuilder了，不然之前行的内容就丢失了
            if (!inBlock) newLine = new StringBuilder();
            for (int i = 0; i < cs.length; i++) {
                if (i + 1 < cs.length && !inBlock && cs[i] == '/' && cs[i + 1] == '*') {
                    inBlock = true;
                    i++;
                } else if (i + 1 < cs.length && inBlock && cs[i] == '*' && cs[i + 1] == '/') {
                    inBlock = false;
                    i++;
                } else if (i + 1 < cs.length && !inBlock && cs[i] == '/' && cs[i + 1] == '/') {
                    break;
                } else if (!inBlock) {
                    newLine.append(cs[i]);
                }
            }

            if (!inBlock && newLine.length() > 0) {
                ans.add(newLine.toString());
            }
        }
        return ans;
    }
}
