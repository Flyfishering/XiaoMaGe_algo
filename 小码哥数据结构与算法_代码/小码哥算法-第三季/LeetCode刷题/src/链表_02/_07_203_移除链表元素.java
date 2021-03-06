package 链表_02;/*
* 删除链表中等于给定值 val 的所有节点。

示例:

输入: 1->2->6->3->4->5->6, val = 6
输出: 1->2->3->4->5

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/remove-linked-list-elements
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */



public class _07_203_移除链表元素 {

    private class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;

        ListNode newHead = new ListNode(0);
        newHead.next = head;

        ListNode curNode = newHead;

        while (curNode.next != null ) {
            if (curNode.next.val == val) {
                curNode.next = curNode.next.next;
            } else  {
                curNode = curNode.next;
            }
        }
        return newHead.next;
    }
}
