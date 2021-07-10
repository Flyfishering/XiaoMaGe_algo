package 链表;

/**
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 */
public class _19_删除链表的倒数第N个结点 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyHead = new ListNode(0, head);
        ListNode slow = dummyHead;
        ListNode fast = dummyHead;

        while (n > 0) {
            fast = fast.next;
            n--;
        }

        ListNode prev = slow;
        while (fast != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next;
        }

        prev.next = slow.next;
        slow.next = null;

        return dummyHead.next;
    }

}
