package 栈队列_03;

/*
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/min-stack-lcci
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
public class _23_面试题_03_02_栈的最小值 {
    private Node head;

    public _23_面试题_03_02_栈的最小值() {
        head = new Node(0,Integer.MAX_VALUE,null);
    }

    public void push(int x) {
        head = new Node(x,Math.min(x,head.min),head);
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.value;
    }

    public int getMin() {
        return head.min;
    }

    private class Node {
        public int value;
        public int min;
        public Node next;

        public Node(int val, int min, Node next) {
            this.value = val;
            this.min = min;
            this.next = next;
        }
    }
}

