package 栈队列_03;

import java.util.Stack;

/*
* https://leetcode-cn.com/problems/daily-temperatures/
* */
public class _739_每日温度 {
    /*
    * 解法1: 使用栈的方式去找右边第一个比我大的值
    * */
    public int[] dailyTemperatures_stack(int[] T) {
        if (T == null || T.length == 0) return null;
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[T.length];

        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty() && T[stack.peek()] < T[i]) {
                res[stack.peek()] = i - stack.peek();
                stack.pop();
            }
            stack.push(i);
        }
        return res;
    }

    /*
    * 第二种解法: 使用动态规划的思想,由后边的值取推出前面的值
    *  i 用来扫描所有的元素，从右往左扫描(逐渐递减），一开始 指向倒数第2个元素
        对于每一个i,一开始令j=i+1
        ① 如果T[i]<T[j] 那么values[i] = j-i 然后i--
        ② 如果values[j]==0 那么values[i]==0 然后i--
        ③ 否则，设置j=j+values[j] 回到步骤①
    * */
    public int[] dailyTemperatures(int[] T) {
        if (T == null || T.length == 0) return null;
        int[] res = new int[T.length];
        for (int i = T.length - 2; i >= 0; i--) {
            int j = i + 1;
            while (true) {
                if (T[i] < T[j]) {
                    res[i] = j - i;
                    break;
                } else if(res[j] == 0) {
                    res[i] = 0;
                    break;
                }
                j = res[j] + j;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        _739_每日温度 o = new _739_每日温度();
        int[] nums = {73, 74, 75, 71, 69, 72, 76, 73};
        o.dailyTemperatures(nums);
    }
}
