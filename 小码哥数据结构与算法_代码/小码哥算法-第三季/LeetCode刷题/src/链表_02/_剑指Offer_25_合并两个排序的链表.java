package 链表_02;

/*
* https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/
* */
public class _剑指Offer_25_合并两个排序的链表 extends _00_baseList {
    /*
    * 方法1: 递归实现
    * */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val <= l2.val) {
            l1.next = mergeTwoLists(l1.next,l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1,l2.next);
            return l2;
        }
    }

    /*
    * 方法2:迭代实现
    * */
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur = cur.next = l1;
                l1 = l1.next;
            } else  {
                cur = cur.next = l2;
                l2 = l2.next;
            }
        }
        if (l1 != null) {
            cur.next = l1;
        } else if (l2 != null) {
            cur.next = l2;
        }
        return dummyHead.next;
    }
}
