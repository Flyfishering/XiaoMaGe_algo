package 链表;

/**
 * https://leetcode-cn.com/problems/partition-list/
 */
public class _86_分隔链表 {
    public ListNode partition(ListNode head, int x) {
        ListNode lHead = new ListNode(0);
        ListNode lTail = lHead;
        ListNode rHead = new ListNode(0);
        ListNode rTail = rHead;

        while (head != null) {
            if (head.val < x) {
                lTail.next = head;
                lTail = head;
            } else {
                rTail.next = head;
                rTail = head;
            }
            head = head.next;
        }
        // 注意清楚尾节点的next指针！！！
        /*
         * 因为可能出现这样的情况:
         * 原链表倒数第N个节点A的值是>=x的，A后面所有节点的值都是<x的
         * 然后rTail.next最终其实就是A.next
         */
        rTail.next = null;
        lTail.next = rHead.next;
        return lHead.next;
    }
}
