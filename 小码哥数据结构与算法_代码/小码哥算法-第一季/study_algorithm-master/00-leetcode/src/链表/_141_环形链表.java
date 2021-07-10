package 链表;

/**
 * https://leetcode-cn.com/problems/linked-list-cycle/
 * @author Rin
 *
 */
public class _141_环形链表 {
    public boolean hasCycle(ListNode head) {
    	if(head == null || head.next == null) return false;
    	ListNode fast = head.next;
    	ListNode slow = head;
    	
        while(fast != null && fast.next != null) {
        	fast = fast.next.next;
        	slow = slow.next;
        	if(fast == slow) {
        		return true;
        	}
        }
        
        return false;
    }
}
