package 栈_队列;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/daily-temperatures/
 */
public class _739_每日温度 {
    // dp(在1的基础上，节省了T[i] == T[j]的判断)
    public int[] dailyTemperatures(int[] T) {
        if (T == null || T.length == 0) return T;
        int[] ans = new int[T.length];

        for (int i = T.length - 2; i >= 0; i--) {
            int j = i + 1;
            while (true) {
                if (T[i] < T[j]) {
                    ans[i] = j - i;
                    break;
                } else if (ans[j] == 0) {
                    ans[i] = 0;
                    break;
                }
                // T[i] > T[j]的时候，去找比T[j]大的下一个数跟T[i]比较
                // T[i] == T[j]的时候，求得的j必然比i位置的数大，下次循环必然进入T[i] < T[j]分支
                j = j + ans[j];
            }
        }
        return ans;
    }
    // dp
    public int[] dailyTemperatures1(int[] T) {
        if (T == null || T.length == 0) return T;
        int[] ans = new int[T.length];

        for (int i = T.length - 2; i >= 0; i--) {
            int j = i + 1;
            while (true) {
                if (T[i] < T[j]) {
                    ans[i] = j - i;
                    break;
                } else if (ans[j] == 0) {
                        ans[i] = 0;
                        break;
                } else if (T[i] == T[j]) {
                    ans[i] = ans[j] + j - i;
                    break;
                } else {
                    j = j + ans[j]; // 理论上，j最终会进入上面的三个分支，然后结束循环
                }
            }
        }
        return ans;
    }

    // 利用栈求右边第一个大的值
    public int[] dailyTemperatures2(int[] T) {
        int[] ans = new int[T.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
                int topIdx = stack.pop();
                ans[topIdx] = i - topIdx;
            }
            stack.push(i);
        }
        return ans;
    }
}
