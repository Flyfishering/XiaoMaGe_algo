/*
请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点。传入函数的唯一参数为 要被删除的节点 。

 

现有一个链表 -- head = [4,5,1,9]，它可以表示为:



 

示例 1：

输入：head = [4,5,1,9], node = 5
输出：[4,1,9]
解释：给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
示例 2：

输入：head = [4,5,1,9], node = 1
输出：[4,5,9]
解释：给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/delete-node-in-a-linked-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

package 链表_02;


import java.util.List;

public class _237_删除链表中的节点 {
    public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }

    /*
    * 思路:找到要删除的元素后,让后一个元素的值覆盖当前元素,然后修改next指向
    * */
    public void deleteNode(ListNode node) {
       node.val = node.next.val;
       node.next = node.next.next;
    }
}
