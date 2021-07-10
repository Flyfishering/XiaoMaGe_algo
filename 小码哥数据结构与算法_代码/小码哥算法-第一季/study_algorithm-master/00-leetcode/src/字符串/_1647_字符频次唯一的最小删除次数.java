package 字符串;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/minimum-deletions-to-make-character-frequencies-unique/
 */
public class _1647_字符频次唯一的最小删除次数 {
    // https://leetcode-cn.com/problems/minimum-deletions-to-make-character-frequencies-unique/solution/tan-xin-on-suan-fa-by-arsenal-591/
    // 贪心。统计频次后排序，优先动大的频次。记录之前的最小频次，若当前频次小于之前的最小频次，则需要将当前频次降到之前最小频次-1
    public int minDeletions(String s) {
        Integer[] cnt = new Integer[26];
        Arrays.fill(cnt, 0);
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }
        Arrays.sort(cnt, (o1, o2) -> (o2 - o1));
        int ans = 0;

        int prev = cnt[0];
        for (int i = 1; i < cnt.length && cnt[i] > 0; i++) {
            if (cnt[i] < prev) {
                prev = cnt[i];
            } else {
                // 频次最低为0次
                prev = Math.max(prev - 1, 0);
                ans += cnt[i] - prev;
            }
        }
        return ans;
    }

    // 利用set去重。。。
    public int minDeletions2(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        Set<Integer> set = new HashSet<>();
        int ans = 0;

        for (int i : cnt) {
            while (set.contains(i)) {
                i--;
                ans++;
            }
            if (i != 0) set.add(i);
        }

        return ans;
    }

    // 排序
    public int minDeletions3(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        Arrays.sort(cnt);
        int ans = 0;

        int i;
        for (i = 24; i >= 0;) {
            if (cnt[i] == 0) {
                break;
            } else if (cnt[i] == cnt[i + 1]) {
                cnt[i]--;
                ans++;
                i--;
            } else if (cnt[i] < cnt[i + 1]) {
                i--;
            } else {
                cnt[i]--;
                ans++;
            }
        }

        // 如果cnt[i]是减小到0的，那么索引比i小的，也都需要减小至0
        for (int j = 0; j < i; j++) {
            ans += cnt[j];
        }

        return ans;
    }

}
