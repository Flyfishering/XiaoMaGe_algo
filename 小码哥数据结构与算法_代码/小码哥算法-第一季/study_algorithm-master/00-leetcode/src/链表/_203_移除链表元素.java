package 链表;

/**
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 * @author Rin
 *
 */
public class _203_移除链表元素 {
	// 递归解法
    public ListNode removeElements(ListNode head, int val) {
        if(head == null) return head;
        
//        head.next = removeElements(head.next, val);
//        if(head.val == val) {
//        	return head.next;
//        } else {
//        	return head;
//        }
        
        ListNode newHead = removeElements(head.next, val);
        if(head.val == val) {
            head = newHead;
        } else {
            head.next = newHead;
        }
        return head;
    }
    
    // 非递归解法
    // 需要有效的处理头节点元素跟val相等时的情况
    public ListNode removeElements2(ListNode head, int val) {
        while(head != null && head.val == val) {
        	head = head.next;
        }
        if(head == null) return head;
        
        ListNode prev = head;
        while(prev.next != null) {
        	if(prev.next.val == val) {
        		prev.next = prev.next.next;
        	} else {
        		prev = prev.next;
        	}
        }
        return head;
    }
    
    // 虚拟头节点
    public ListNode removeElements3(ListNode head, int val) {
        ListNode dummyNode = new ListNode(val - 1);
        dummyNode.next = head;
        ListNode prev = dummyNode;
        
        while(dummyNode.next != null) {
        	if(dummyNode.next.val == val) {
        		dummyNode.next = dummyNode.next.next;
        	} else {
        		dummyNode = dummyNode.next;
        	}
        }
        return prev.next;
        
    }
    
}


