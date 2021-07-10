package 数学;

/**
 * https://leetcode-cn.com/problems/rectangle-overlap/
 */
public class _836_矩形重叠 {
    // x1, y1, x2, y2
    // 0,  1,  2,  3
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        if (rec1[0] == rec1[2] || rec1[1] == rec1[3] || rec2[0] == rec2[2] || rec2[1] == rec2[3]) {
            return false;
        }

        return !(rec2[0] >= rec1[2]     // 2在1的右边
                || rec2[2] <= rec1[0]   // 2在1的左边
                || rec2[3] <= rec1[1]   // 2在1的下边
                || rec2[1] >= rec1[3]); // 2在1的上边
    }
}
