package 栈;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/basic-calculator-ii/
 */
public class _227_基本计算器_II {
    // https://leetcode-cn.com/problems/basic-calculator-ii/solution/dan-zhan-jian-ji-xiao-lu-by-cyingenohalt-yjh9/
    public int calculate(String s) {
        char[] cs = s.toCharArray();
        Deque<Integer> st_nums = new LinkedList<>();
        int sign = 1;
        char op = '#';
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == ' ') continue;
            if (cs[i] >= '0' && cs[i] <= '9') {
                int num = cs[i] - '0';
                while (i < cs.length - 1 && cs[i + 1] >= '0' && cs[i + 1] <= '9') {
                    num = num * 10 + (cs[++i] - '0');
                }
                if (op == '*') {
                    st_nums.push(num * st_nums.pop());
                    op = '#';
                } else if (op == '/') {
                    st_nums.push(st_nums.pop() / num);
                    op = '#';
                } else {
                    st_nums.push(sign * num);
                }
            } else if (cs[i] == '+' || cs[i] == '-') {
                sign = cs[i] == '+' ? 1 : -1;
            } else if (cs[i] == '*') {
                op = '*';
            } else {
                op = '/';
            }
        }
        int ans = 0;
        while (!st_nums.isEmpty()) {
            ans += st_nums.pop();
        }
        return ans;
    }

    // https://leetcode-cn.com/problems/basic-calculator-ii/solution/shu-ju-jie-gou-he-suan-fa-shi-yong-zhan-bv6h5/
    // 第二种解法感觉更加简洁
    public int calculate2(String s) {
        char[] cs = s.toCharArray();
        int preOp = '+';
        Deque<Integer> stack = new LinkedList<>();
        int ans = 0;

        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == ' ') continue;
            if (cs[i] >= '0' && cs[i] <= '9') {
                int num = cs[i] - '0';
                while (i < cs.length - 1 && cs[i + 1] >= '0' && cs[i + 1] <= '9') {
                    num = num * 10 + (cs[++i] - '0');
                }
                if (preOp == '+') {
                    stack.push(num);
                } else if (preOp == '-') {
                    stack.push(-num);
                } else if (preOp == '*') {
                    stack.push(stack.pop() * num);
                } else {
                    stack.push(stack.pop() / num);
                }
            } else {
                preOp = cs[i];
            }
        }
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }

}
