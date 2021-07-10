package 链表_02;

import java.util.List;

/*
* 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。

示例 1:

输入: 1->1->2
输出: 1->2
示例 2:

输入: 1->1->2->3->3
输出: 1->2->3
通过次数187,721提交次数360,247


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
public class _83_删除排序链表中的重复元素 {
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    /*
    * 不需要双指针,其实一个就够
    * */
//    public ListNode deleteDuplicates(ListNode head) {
//        if (head == null || head.next == null) return head;
//        ListNode firstP = head;
//        ListNode secondP = head.next;
//        while (secondP != null) {
//            if (firstP.val == secondP.val) {
//                secondP = secondP.next;
//                firstP.next = secondP;
//            } else {
//                firstP = firstP.next;
//                secondP = secondP.next;
//            }
//        }
//        return head;
//    }

    public ListNode deleteDuplicates1(ListNode head) {
        ListNode curNode = head;
        while (curNode != null && curNode.next != null) {
            if (curNode.val == curNode.next.val) {
               curNode.next = curNode.next.next;
            } else {
                curNode = curNode.next;
            }
        }
        return head;
    }
}
