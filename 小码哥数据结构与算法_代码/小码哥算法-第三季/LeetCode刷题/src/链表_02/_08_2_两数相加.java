package 链表_02;

/*
* 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

示例：

输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/add-two-numbers
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
public class _08_2_两数相加 {

    private class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
       if (l1 == null) return l2;
       if (l2 == null) return l1;

       ListNode dummyHead = new ListNode(0);
       ListNode lastNode = dummyHead;
       int carry = 0;

       while (l1 != null || l2 != null) {
           int v1 = 0;
           if (l1 != null) {
               v1 = l1.val;
               l1 = l1.next;
           }
           int v2 = 0;
           if (l2 != null) {
               v2 = l2.val;
               l2 = l2.next;
           }
           int sum  = v1 + v2 + carry;
           carry = sum / 10; // 设置进位
           lastNode.next = new ListNode(sum % 10);
           lastNode = lastNode.next;
       }
       if (carry > 0) {
           lastNode.next = new ListNode(carry);
       }
       return dummyHead.next;
    }
}
