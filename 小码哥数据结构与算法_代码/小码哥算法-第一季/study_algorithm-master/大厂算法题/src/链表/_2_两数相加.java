package 链表;

/**
 * https://leetcode-cn.com/problems/add-two-numbers/
 */
public class _2_两数相加 {

    // 课件解法
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode dummyHead = new ListNode(0);
        ListNode last = dummyHead;
        // 进位值
        int carry = 0;

        while (l1 != null || l2 != null) {
            int v1 = 0;
            int v2 = 0;
            if (l1 != null) {
                v1 = l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                v2 = l2.val;
                l2 = l2.next;
            }
            int sum = v1 + v2 + carry;
            // 设置进位值
            carry = sum / 10;
            // sum的个位数作为新节点的值
            last.next = new ListNode(sum % 10);
            last = last.next;
        }

        // 检查最后的进位
        if (carry != 0) {
            last.next = new ListNode(1);
        }
        return dummyHead.next;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode dummyHead = new ListNode(0);
        ListNode last = dummyHead;
        int sum = 0;
        // 加法持续到l1,l2都不为空为止
        while (l1 != null || l2 != null) {
            int v1 = l1 == null ? 0 : l1.val;
            int v2 = l2 == null ? 0 : l2.val;

            sum = v1 + v2 + (sum / 10);
            ListNode node = new ListNode(sum % 10);
            last.next = node;
            last = last.next;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if ((sum / 10) != 0) {
            ListNode node = new ListNode(1);
            last.next = node;
        }
        return dummyHead.next;
    }
}
