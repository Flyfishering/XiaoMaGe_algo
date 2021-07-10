package 链表;

/**
 * https://leetcode-cn.com/problems/reverse-linked-list/
 * @author Rin
 *
 */
public class _206_反转链表 {
	// 递归解法
    public ListNode reverseList(ListNode head) {
    	if(head == null || head.next == null) return head;
    	ListNode newHead = reverseList(head.next);
    	head.next.next = head;
    	head.next = null;
    	return newHead;
    }
    
	// 迭代解法
    public ListNode reverseList2(ListNode head) {
    	ListNode newHead = null;
    	ListNode next = null;
    	while(head != null) {
    		next = head.next;
        	head.next = newHead;
        	newHead = head;
        	head = next;
    	}

    	return newHead;
    }
}
