package 链表;

/**
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
 */
public class _83_删除排序链表中的重复元素 {
    // 递归
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        // 当前节点与下一个节点的值相等
        if (head.val == head.next.val) {
            head = deleteDuplicates(head.next);
        } else {
            head.next = deleteDuplicates(head.next);
        }
        return head;
    }

    // 迭代
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }
}
