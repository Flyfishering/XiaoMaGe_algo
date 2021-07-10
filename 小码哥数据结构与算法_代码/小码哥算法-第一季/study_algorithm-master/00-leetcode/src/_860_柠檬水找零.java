import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/lemonade-change/
 */
public class _860_柠檬水找零 {
    public boolean lemonadeChange(int[] bills) {

        int num5 = 0;
        int num10 = 0;
        for (int bill : bills) {
            if (bill == 5) {
                num5++;
            } else if (bill == 10) {
                if (num5 == 0) {
                    return false;
                } else {
                    num5--;
                    num10++;
                }
            } else {
                if (num10 == 0) {
                    if (num5 < 3) {
                        return false;
                    } else {
                        num5 -= 3;
                    }
                } else {
                    if (num5 == 0) {
                        return false;
                    } else {
                        num10--;
                        num5--;
                    }
                }
            }
        }
        return true;
    }
}
