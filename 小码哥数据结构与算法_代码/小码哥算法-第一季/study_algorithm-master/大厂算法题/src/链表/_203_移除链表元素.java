package 链表;

/**
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 */
public class _203_移除链表元素 {

    // 构建一个新链表(特别复杂)
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        // 新链表的头节点
        ListNode newHead = null;
        // 新链表的尾节点
        ListNode newTail = null;

        while (head != null) {
            if (head.val != val) {
                if (newTail == null) {
                    newTail = head;
                    newHead = head;
                } else {
                    newTail.next = head;
                    newTail = head;
                }
            }
            head = head.next;
        }

        if (newTail == null) {
            return null;
        }
        // 尾节点的next要清空
        newTail.next = null;
        return newHead;
    }

    // 虚拟头节点(课件解法)
    public ListNode removeElements2(ListNode head, int val) {
        ListNode newHead = new ListNode(0);
        ListNode newTail = newHead;

        while (head != null) {
            if (head.val != val) {
                newTail.next = head;
                newTail = head;
            }
            head = head.next;
        }
        // 清空尾节点的next
        newTail.next = null;
        return newHead.next;
    }

    // 虚拟头节点
    public ListNode removeElements3(ListNode head, int val) {
        ListNode first = new ListNode(0, head);
        ListNode node = first;

        while (node.next != null) {
            if (node.next.val == val) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }
        }

        return first.next;
    }
}
