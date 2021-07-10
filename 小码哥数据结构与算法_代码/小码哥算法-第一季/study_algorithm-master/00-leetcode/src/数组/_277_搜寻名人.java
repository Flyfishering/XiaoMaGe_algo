package 数组;

/**
 * https://leetcode-cn.com/problems/find-the-celebrity/
 */
public class _277_搜寻名人 {
    public int findCelebrity(int n) {
        int result = 0;
        for (int i = 1; i < n; i++) {
            // 名人不认识任何人，可以排除result
            if(knows(result, i)) result = i;
        }

        // 由于数组中可能不存在名人，需要对result再次确认
        for (int i = 0; i < n; i++) {
            if (i == result) continue;
            if (!knows(i, result) || knows(result, i)) return -1;
        }

        return result;
    }

    boolean knows(int a, int b) {return false;}
}
