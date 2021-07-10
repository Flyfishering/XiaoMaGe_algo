package 链表;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/copy-list-with-random-pointer/
 */
public class _138_复制带随机指针的链表 {
    // 不利用hashmap的解。
    // 具体做法是把新节点放在原节点后面
    public Node copyRandomList1(Node head) {
        if (head == null) return null;
        Node cur = head;

        // 将新节点拼接到复制元节点的后面
        while (cur != null) {
            Node node = new Node(cur.val);
            node.next = cur.next;
            cur.next = node;
            cur = node.next;
        }

        // 给新节点的random赋值
        cur = head;
        while (cur != null) {
            cur.next.random = cur.random == null ? null : cur.random.next;
            cur = cur.next.next;
        }

        // 新旧节点拆分
        Node dummyHead = new Node(0);
        Node newTail = dummyHead;
        cur = head;

        while (cur != null) {
            newTail.next = cur.next;
            newTail = newTail.next;
            cur.next = cur.next.next;
            cur = cur.next;
        }
        return dummyHead.next;
    }

    // 两次遍历。第一次遍历，把新旧节点都放入map。
    // 第二次遍历，利用map给新节点连线(next和random)
    // https://leetcode-cn.com/problems/copy-list-with-random-pointer/solution/liang-chong-shi-xian-tu-jie-138-fu-zhi-dai-sui-ji-/
    public Node copyRandomList2(Node head) {
        if (head == null) return null;
        Node cur = head;
        HashMap<Node, Node> map = new HashMap<>();

        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }

        cur = head;
        while (cur != null) {
            Node node = map.get(cur);
            node.next = cur.next == null ? null : map.get(cur.next);
            node.random = cur.random == null ? null : map.get(cur.random);
            cur = cur.next;
        }

        return map.get(head);
    }

    // 自力
    public Node copyRandomList3(Node head) {
        Node dummyHead = new Node(0);
        Node newTail = dummyHead;
        Node cur = head;
        Map<Node, Node> map = new HashMap<>();

        while (cur != null) {
            newTail.next = new Node(cur.val);
            newTail = newTail.next;
            newTail.random = cur.random;
            map.put(cur, newTail);
            cur = cur.next;
        }

        newTail = dummyHead.next;

        while (newTail != null) {
            newTail.random = map.get(newTail.random);
            newTail = newTail.next;
        }
        return dummyHead.next;
    }
}
