package 二叉树;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/verify-preorder-serialization-of-a-binary-tree/
 */
public class _331_验证二叉树的前序序列化 {
    // 每个增加一个非空节点会消耗一个槽位，然后增加两个槽位
    // 每增加一个空节点会消耗一个槽位
    // 官方题解1
    // https://leetcode-cn.com/problems/verify-preorder-serialization-of-a-binary-tree/solution/yan-zheng-er-cha-shu-de-qian-xu-xu-lie-h-jghn/
    public boolean isValidSerialization(String preorder) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        int len = preorder.length();

        for (int i = 0; i < len; i++) {
            if (preorder.charAt(i) == '#') {
                if (stack.isEmpty()) return false;
                int top = stack.pop() - 1;
                if (top > 0) {
                    stack.push(top);
                }
            } else if (preorder.charAt(i) != ','){
                while (i < len && preorder.charAt(i) != ',') {
                    i++;
                }
                if (stack.isEmpty()) return false;
                int top = stack.pop() - 1;
                if (top > 0) {
                    stack.push(top);
                }
                stack.push(2);
            }
        }
        return stack.isEmpty();
    }

    // 官方题解2，在1的基础上，省去了栈
    public boolean isValidSerialization2(String preorder) {
        int slots = 1;
        int len = preorder.length();

        for (int i = 0; i < len; i++) {
            if (preorder.charAt(i) == '#') {
                if (slots < 1) return false;
                slots--;
            } else if (preorder.charAt(i) != ',') {
                while (i < len && preorder.charAt(i) != ',') { // 读一个数字
                    i++;
                }
                if (slots < 1) return false;
                slots++;
            }
        }
        return slots == 0;
    }

    // https://leetcode-cn.com/problems/verify-preorder-serialization-of-a-binary-tree/solution/pai-an-jiao-jue-de-liang-chong-jie-fa-zh-66nt/
    // 上面这个解法是利用栈，或者利用入度出度去计算。可以参考下。
}
