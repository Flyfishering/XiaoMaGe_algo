package 字符串;

/**
 * https://leetcode-cn.com/problems/minimum-window-substring/
 */
public class _76_最小覆盖子串 {
    // https://leetcode-cn.com/problems/minimum-window-substring/solution/tong-su-qie-xiang-xi-de-miao-shu-hua-dong-chuang-k/
    public String minWindow(String s, String t) {
        int[] needs = new int[128];
        // 通过needCnt来判断滑窗中是否包含所有必须元素。为0的时候，表示全部包含。大于0的时候，表示还缺的元素个数
        int needCnt = t.length();
        for (char c : t.toCharArray()) {
            needs[c]++;
        }

        char[] cs = s.toCharArray();
        int l = 0, r = 0, minSize = Integer.MAX_VALUE;
        int start = 0;
        while (r < cs.length) {
            if (needs[cs[r]] > 0) { // r遍历到一个需要的字符
                needCnt--;
            }
            needs[cs[r]]--;

            if (needCnt == 0) { // 窗口中已经包含了所有需要的字符,开始缩左边界
                while (needs[cs[l]] < 0) { // 如果遇到的是多余的字符，那么跳过即可
                    needs[cs[l]]++;
                    l++;
                }
                // 找到了必要的字符,可以更新长度
                int len = r - l + 1;
                if (len < minSize) { // 若新的窗口长度是最短的，则更新长度，以及记录一下这个串的开始位置
                    minSize = len;
                    start = l;
                }
                needCnt++;
                needs[cs[l]]++;
                l++;
            }
            r++; // 扩展右边界
        }
        return minSize == Integer.MAX_VALUE ? "" : s.substring(start, start + minSize);
    }
}
