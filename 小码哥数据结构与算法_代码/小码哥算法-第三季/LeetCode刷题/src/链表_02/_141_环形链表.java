package 链表_02;

public class _141_环形链表 {
    class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
    }

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode slowP = head;
        ListNode fastP = head.next;
        while (fastP != null && fastP.next != null){
            if (fastP == slowP) return true;
            fastP = fastP.next.next;
            slowP = slowP.next;
        }
        return false;
    }
}
