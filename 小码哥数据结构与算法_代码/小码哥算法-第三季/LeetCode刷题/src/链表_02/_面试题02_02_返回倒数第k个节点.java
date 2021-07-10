package 链表_02;

/*
* https://leetcode-cn.com/problems/kth-node-from-end-of-list-lcci/
* */
public class _面试题02_02_返回倒数第k个节点 extends _00_baseList {
    public int kthToLast(ListNode head, int k) {
        ListNode slowP = head;
        ListNode fastP = head;
        while (k-- > 0) {
            fastP = fastP.next;
        }
        while (fastP != null) {
            slowP = slowP.next;
            fastP = fastP.next;
        }
        return slowP.val;
    }
}
