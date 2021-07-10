/*
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/reverse-linked-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
package 链表_02;

public class _206_反转链表 {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /*
    * 1.迭代实现
    * tmp指向head的next
    * head的next先指向新节点
    * 新节点指向head
    * head指向tmp
    * */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = null;
        while (head != null) {
            ListNode tmp = head.next;
            head.next = newHead;
            newHead = head;
            head = tmp;
        }
        return newHead;
    }

    /*
    * 2.递归实现
    * */
    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = reverseList(head.next);
        head.next.next = head; // 假设后边的已经全部反转,只差头结点还没有,那么这句话其实就是让第二个元素指向第一个元素
        head.next = null;      // 然后第一个元素指向空
        return newHead;
    }
}
