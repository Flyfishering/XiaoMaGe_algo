package 栈;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
 */
public class _150_逆波兰表达式求值 {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if ("+-*/".contains(token)) { // 是符号
                int right = stack.pop();
                int left = stack.pop();
                stack.push(calculate(left, right, token));
            } else { // 是数字
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    private int calculate(Integer left, Integer right, String operator) {
        switch (operator) {
            case "+" : return left + right;
            case "-" : return left - right;
            case "*" : return left * right;
            default : return left / right;
        }
    }

    // 在1的基础上稍微优化了代码结构，减少了一次符号判断
    public int evalRPN2(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            switch (token) {
                case "+" :
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "*" :
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "-" : {
                    Integer right = stack.pop();
                    stack.push(stack.pop() - right);
                    break;
                }
                case "/" : {
                    Integer right = stack.pop();
                    stack.push(stack.pop() / right);
                    break;
                }
                default : stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
}
