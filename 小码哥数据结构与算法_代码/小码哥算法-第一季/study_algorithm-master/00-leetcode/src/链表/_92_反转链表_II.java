package 链表;

/**
 * https://leetcode-cn.com/problems/reverse-linked-list-ii/
 */
public class _92_反转链表_II {
    // 官方题解1
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode preNode = dummyHead;

        // 从虚拟头节点出发，走left - 1步找到left节点的前继节点
        for (int i = 0; i < left - 1; i++) {
            preNode = preNode.next;
        }

        ListNode rightNode = preNode;
        // 从前继节点出发，走right - left + 1步到达右节点
        for (int i = 0; i < right - left + 1; i++) {
            rightNode = rightNode.next;
        }
        // 保留右节点的后继节点
        ListNode successorNode = rightNode.next;
        // 左节点
        ListNode leftNode = preNode.next;

        // 切割出需要反转的链表
        rightNode.next = null;
        preNode.next = null;

        // 反转链表
        reverse(leftNode);

        // 重新接线
        preNode.next = rightNode;
        leftNode.next = successorNode;

        return dummyHead.next;
    }

    private void reverse(ListNode head) {
        ListNode newHead = null;
        ListNode cur = head;
        ListNode next = null;

        while (cur != null) {
            next = cur.next;
            cur.next = newHead;
            newHead = cur;
            cur = next;
        }
    }

    // 头插法，一次遍历
    public ListNode reverseBetween2(ListNode head, int left, int right) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode preNode = dummyHead;

        // 从虚拟头节点出发，走left - 1步找到前继节点
        for (int i = 0; i < left - 1; i++) {
            preNode = preNode.next;
        }

        // tail表示已经反转过的节点
        ListNode tail = preNode.next;
        // cur用来表示即将反转的节点
        ListNode cur = tail.next;

        // 左子节点到右子节点之间的元素，进行反转(头插，插到前继节点后面)。头插的次数为right - left
        for (int i = 0; i < right - left; i++) {
            ListNode next = cur.next;
            cur.next = preNode.next;
            preNode.next = cur;
            tail.next = next;
            cur = next;
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        _92_反转链表_II test = new _92_反转链表_II();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        test.reverseBetween(node1, 2, 4);

    }
}
