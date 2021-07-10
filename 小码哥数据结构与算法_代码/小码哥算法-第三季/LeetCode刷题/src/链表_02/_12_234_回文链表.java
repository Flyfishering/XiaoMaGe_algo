package 链表_02;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*
* https://leetcode-cn.com/problems/palindrome-linked-list/
* */
public class _12_234_回文链表 extends _00_baseList {
    /*
    * 解法1:找到中间节点,然后反转节点之后的节点,然后两个头依次往下比较
    * */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        if (head.next.next == null) return head.val == head.next.val;

        ListNode midNode = mideNode(head);
        ListNode rHead = reversal(midNode.next);
        ListNode lHead = head;

        while (rHead != null) {
            if (lHead.val != rHead.val) return false;
            lHead = lHead.next;
            rHead = rHead.next;
        }
        return true;
    }

    public ListNode reversal(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode tmp = head.next;
            head.next = newHead;
            newHead = head;
            head = tmp;
        }
        return newHead;
    }

    public ListNode mideNode(ListNode head) {
        ListNode sp = head;
        ListNode fp = head;
        while (fp.next != null && fp.next.next != null) { // 这里的条件决定了如果是偶数个元素,中间节点是取谁
            sp = sp.next;
            fp = fp.next.next;
        }
        return sp;
    }


    /*
    * 解法2:不打乱链表的结构,借用栈实现(性能不行)
    * */
    public boolean isPalindrome1(ListNode head) {
        if (head == null || head.next == null) return true;
        if (head.next.next == null) return head.val == head.next.val;

        Stack<ListNode> stack = new Stack<>();

        ListNode sp = head;
        ListNode fp = head;
        while (fp.next != null && fp.next.next != null) {
            stack.push(sp);
            sp = sp.next;
            fp = fp.next.next;
        }
        if (fp.next != null) {
            stack.push(sp);
        }
        ListNode midNode = sp.next;

        while (midNode != null) {
            ListNode pop = stack.pop();
            System.out.println(pop.val);
            System.out.println(midNode.val);

            if (pop.val != midNode.val) return false;
            midNode = midNode.next;
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
