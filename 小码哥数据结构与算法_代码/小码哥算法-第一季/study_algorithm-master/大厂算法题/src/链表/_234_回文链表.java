package 链表;

/**
 * https://leetcode-cn.com/problems/palindrome-linked-list/
 */
public class _234_回文链表 {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        if (head.next.next == null) return head.val == head.next.val;

        // 找到中间节点
        ListNode mid = middleNode(head);
        // 翻转右半部分(中间节点的下一个节点)
        ListNode rHead = reverseList(mid.next);
        ListNode lHead = head;

        // 从lHead和rHead出发，判断是否为回文链表
        while (rHead != null) {
            if (rHead.val != lHead.val) {
                return false;
            }
            rHead = rHead.next;
            lHead = lHead.next;
        }
        return true;
    }

    // 找到中间节点(右半部分链表头节点的前一个节点)
    private ListNode middleNode(ListNode head) {
        // 利用快慢指针(奇数节点，偶数节点，自己画图试一试！)
        ListNode slow = head.next;
        ListNode fast = head.next.next;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // 翻转链表
    private ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode newHead = null;
        ListNode next = null;

        // 不断的往newHead前面插入新的节点
        while (head != null) {
            next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }
}