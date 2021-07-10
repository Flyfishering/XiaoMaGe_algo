package 链表;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/add-two-numbers-ii/
 */
public class _445_两数相加_II {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();

        while (l1 != null) {
            stack1.push(l1);
            l1 = l1.next;
        }

        while (l2 != null) {
            stack2.push(l2);
            l2 = l2.next;
        }

        int carry = 0;
        ListNode newHead = null;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            int val1 = stack1.isEmpty() ? 0 : stack1.pop().val;
            int val2 = stack2.isEmpty() ? 0 : stack2.pop().val;
            int sum = val1 + val2 + carry;
            ListNode node = new ListNode(sum % 10);
            carry = sum / 10;
            node.next = newHead;
            newHead = node;
        }
        if (carry != 0) {
            ListNode node = new ListNode(1);
            node.next = newHead;
            newHead = node;
        }

        return newHead;
    }
}
