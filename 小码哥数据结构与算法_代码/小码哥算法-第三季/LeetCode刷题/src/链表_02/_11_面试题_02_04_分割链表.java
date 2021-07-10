package 链表_02;

/*
* https://leetcode-cn.com/problems/partition-list/
* */
public class _11_面试题_02_04_分割链表 extends _00_baseList{
    public ListNode partition(ListNode head, int x) {
        if (head == null) return null;

        ListNode lHead = new ListNode(0);
        ListNode lTail = lHead;
        ListNode rHead = new ListNode(0);
        ListNode rTail = rHead;

        while (head != null) {
            if (head.val < x) {
                lTail.next = head;
                lTail = lTail.next;
            } else {
                rTail.next = head;
                rTail = rTail.next;
            }
            head = head.next;
        }
        rTail.next = null; // 一定要记住清空rTail.next为空,因为如果不设置为null,假如之后的一系列数字都是小于x的,这个rTail是没机会处理的
        lTail.next = rHead.next;
        return lHead.next;
    }
}
