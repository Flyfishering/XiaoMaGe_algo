package 链表;

/**
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/
 */
public class _82_删除排序链表中的重复元素_II {
    // 官方题解的做法。添加dummy节点，然后判断next与next.next是否相等
    // 相等的话，记录下重复的值，然后循环删除跟这个值相等的节点
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode cur = dummyHead;

        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int x = cur.next.val;
                while (cur.next != null && cur.next.val == x) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }
        return dummyHead.next;
    }

    // 递归
    public ListNode deleteDuplicates2(ListNode head) {
        // 没有节点或者只有一个节点，必然没有重复元素
        if (head == null || head.next == null) return head;

        // 当前节点和下一个节点，值不同，则head的值是需要保留的，对head.next继续递归
        if (head.val != head.next.val) {
            head.next = deleteDuplicates2(head.next);
            return head;
        } else {
            // 当前节点与下一个节点的值重复了，重复的值都不能要。
            // 一直往下找，找到不重复的节点。返回对不重复节点的递归结果
            ListNode notDup = head.next.next;
            while (notDup != null && notDup.val == head.val) {
                notDup = notDup.next;
            }
            return deleteDuplicates2(notDup);
        }
    }

    // 另一种迭代解
    // https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/solution/fu-xue-ming-zhu-di-gui-die-dai-yi-pian-t-wy0h/
    public ListNode deleteDuplicates3(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummyHead = new ListNode(0, head);
        ListNode pre = dummyHead;
        ListNode cur = head;

        while (cur != null && cur.next != null) {
            if (cur.val != cur.next.val) {
                pre = cur;
                cur = cur.next;
            } else {
                while (cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
                cur = cur.next;
                pre.next = cur;
            }
        }
        return dummyHead.next;
    }

    // 乱写的解。。。
    public ListNode deleteDuplicates4(ListNode head) {
        if (head == null) return null;
        ListNode cur = head.next;
        ListNode notDup = head;
        ListNode newHead = new ListNode(0);
        ListNode tail = newHead;

        while (cur != null) {
            if (cur.val != notDup.val) {
                if (notDup.next == cur) {
                    tail.next = notDup;
                    tail = tail.next;
                }
                notDup = cur;
            }
            cur = cur.next;
        }
        if (notDup.next == null) {
            tail.next = notDup;
        } else {
            tail.next = null;
        }

        return newHead.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(4);
        ListNode node7 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        ListNode res = new _82_删除排序链表中的重复元素_II().deleteDuplicates3(node1);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }
}
