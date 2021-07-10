package 链表;

/**
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists-lcci/
 */
public class _面试题_02_07_链表相交 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA = headA;
        ListNode curB = headB;

        while (curA != curB) {
            curA = curA == null ? headB : curA.next;
            curB = curB == null ? headA : curB.next;
        }
        return curA;
    }
}
