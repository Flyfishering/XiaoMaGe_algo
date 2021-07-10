package 链表;

/**
 * https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/
 */
public class 剑指_Offer_22_链表中倒数第k个节点 {
    // 向前走k-1步
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode next = head;
        while (--k > 0 && next != null) {
            next = next.next;
        }
        if (next == null) return null;
        while (next.next != null) {
            head = head.next;
            next = next.next;
        }
        return head;
    }

    // 向前走k步
    public ListNode getKthFromEnd2(ListNode head, int k) {
        ListNode next = head;
        while (k-- > 0) {
            next = next.next;
        }

        while (next != null) {
            head = head.next;
            next = next.next;
        }

        return head;
    }
}
