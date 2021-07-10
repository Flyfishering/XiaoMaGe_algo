package 链表;

import java.util.PriorityQueue;

/**
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/
 */
public class _23_合并K个升序链表 {
    // 分治
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        ListNode newHead = mergeKLists(lists, 0, lists.length);
        return newHead;
    }

    private ListNode mergeKLists(ListNode[] lists, int begin, int end) {
        if (end - begin < 2) return lists[begin];
        int mid = (begin + end) >> 1;
        ListNode leftHead = mergeKLists(lists, begin, mid);
        ListNode rightHead = mergeKLists(lists, mid, end);
        return merge(leftHead, rightHead);
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;

        while (left != null && right != null) {
            if (left.val <= right.val) {
                tail.next = left;
                tail = tail.next;
                left = left.next;
            } else {
                tail.next = right;
                tail = tail.next;
                right = right.next;
            }
        }
        if (left != null) {
            tail.next = left;
        } else {
            tail.next = right;
        }
        return dummyHead.next;
    }

    // 利用堆
    public ListNode mergeKLists2(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>((ListNode n1, ListNode n2) -> {
            return n1.val - n2.val;
        });
        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;

        for (ListNode listNode : lists) {
            while (listNode != null) {
                queue.offer(listNode);
                listNode = listNode.next;
            }
        }
        while (!queue.isEmpty()) {
            tail.next = queue.poll();
            tail = tail.next;
        }
        tail.next = null;
        return dummyHead.next;
    }

    // 暴力出奇迹
    public ListNode mergeKLists3(ListNode[] lists) {
        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;

        while (true) {
            int minNodeIdx = -1; // 最小节点所在链表的数组索引
            int minVal = Integer.MAX_VALUE;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] == null) continue;
                if (lists[i].val < minVal) {
                    minNodeIdx = i;
                    minVal = lists[i].val;
                }
            }
            if (minNodeIdx == -1) break;
            tail.next = lists[minNodeIdx];
            tail = tail.next;
            lists[minNodeIdx] = lists[minNodeIdx].next;
        }
        return dummyHead.next;
    }
}
