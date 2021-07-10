package 链表;

/**
 * https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/
 * 最优解参照leetcode160_相交链表
 */
public class _剑指_Offer_52_两个链表的第一个公共节点 {
    // 长度长的链表指针先向前走，等两个链表长度相等了，再一起走
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = 0;
        int lenB = 0;
        ListNode curA = headA;
        ListNode curB = headB;

        while (curA != null) {
            lenA++;
            curA = curA.next;
        }

        while (curB != null) {
            lenB++;
            curB = curB.next;
        }

        curB = headB;
        curA = headA;
        if (lenB >= lenA) {
            while (lenB > lenA) {
                curB = curB.next;
                lenB--;
            }
        } else {
            while (lenB < lenA) {
                curA = curA.next;
                lenA--;
            }
        }

        while (curB != null) {
            if (curB == curA) {
                return curB;
            }
            curB = curB.next;
            curA = curA.next;
        }

        return null;
    }
}
