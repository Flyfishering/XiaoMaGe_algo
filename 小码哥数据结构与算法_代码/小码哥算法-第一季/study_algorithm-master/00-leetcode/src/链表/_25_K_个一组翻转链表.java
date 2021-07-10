package 链表;

/**
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 */
public class _25_K_个一组翻转链表 {
    // 把每一段链表切割出来，单独反转，然后再拼回去。
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1 || head == null || head.next == null) return head;
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode pre = dummyHead;
        ListNode end = dummyHead;

        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            // 若一组节点不足k个了，直接结束
            if (end == null) break;
            ListNode start = pre.next;
            ListNode successor = end.next;

            // 要反转部分切割出来
            pre.next = null;
            end.next = null;

            // 开始反转
            reverse(start);

            // 重新连线
            pre.next = end;
            start.next = successor;

            // pre和end重新赋值
            pre = start;
            end = pre;
        }

        return dummyHead.next;
    }

    private void reverse(ListNode head) {
        ListNode cur = head;
        ListNode newHead = null;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = newHead;
            newHead = cur;
            cur = next;
        }
    }

    // 头插法
    public ListNode reverseKGroup2(ListNode head, int k) {
        if (k == 1 || head == null || head.next == null) return head;
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode prev = dummyHead;
        ListNode tail = prev.next;

        while (prev.next != null) {
            for (int i = 0; i < k - 1 && tail != null; i++) {
                tail = tail.next;
            }
            if (tail == null) break;
            tail = prev.next;
            ListNode cur = tail.next;
            for(int i = 0; i < k - 1; i++) {
                ListNode next = cur.next;
                cur.next = prev.next;
                prev.next = cur;
                tail.next = next;
                cur = next;
            }

            // 重新给节点赋值
            prev = tail;
            tail = prev.next;
        }
        return dummyHead.next;
    }
    // TODO 尾插法

    public static void main(String[] args) {
        _25_K_个一组翻转链表 test = new _25_K_个一组翻转链表();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        test.reverseKGroup(node1, 2);
    }
}
