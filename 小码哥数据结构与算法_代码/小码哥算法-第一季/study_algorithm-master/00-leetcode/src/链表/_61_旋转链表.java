package 链表;

/**
 * https://leetcode-cn.com/problems/rotate-list/
 */
public class _61_旋转链表 {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        int len = 1;
        ListNode cur = head;

        while (cur.next != null) {
            cur = cur.next;
            len++;
        }

        cur.next = head;
        int move = len - k % len;
        cur = head;

        while(--move > 0) {
            cur = cur.next;
        }

        head = cur.next;
        cur.next = null;

        return head;
    }
}
