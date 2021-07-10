package 栈队列_03;

import java.util.HashMap;
import java.util.Stack;
/*
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/min-stack
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*
* */

// 第一种解法: 两个栈
public class _22_155_最小栈  {
    private Stack<Integer> stack; // 用来存放正常数据
    private Stack<Integer> minStack; // 用来存放最小数据

    public _22_155_最小栈() {
        stack = new Stack<Integer>();
        minStack = new Stack<Integer>();
    }

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty()) {
            minStack.push(x);
        } else {
            if (x < minStack.peek()) {
                minStack.push(x);
            } else {
                minStack.push(minStack.peek());
            }
        }
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
