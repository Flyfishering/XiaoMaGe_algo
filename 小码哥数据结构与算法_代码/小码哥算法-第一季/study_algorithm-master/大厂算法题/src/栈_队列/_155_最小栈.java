package 栈_队列;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/min-stack/
 */
public class _155_最小栈 {
    // 利用一个额外的栈
    class MinStack {
        private Stack<Integer> stack;
        // 最小栈，空间换时间
        private Stack<Integer> minStack;

        public MinStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int x) {
            stack.push(x);
            if (minStack.isEmpty()) {
                minStack.push(x);
            } else {
                minStack.push(Math.min(x, minStack.peek()));
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

    // 利用链表
    class MinStack2 {
        class Node {
            int val;
            int min;
            Node next;

            public Node(int val, int min, Node next) {
                this.val = val;
                this.min = min;
                this.next = next;
            }
        }
        Node head;

        /** initialize your data structure here. */
        public MinStack2() {
            head = new Node(0, Integer.MAX_VALUE, null);
        }

        public void push(int x) {
            int min = Math.min(x, head.min);
            head = new Node(x, min, head);
        }

        public void pop() {
            head = head.next;
        }

        public int top() {
            return head.val;
        }

        public int getMin() {
            return head.min;
        }
    }
}
