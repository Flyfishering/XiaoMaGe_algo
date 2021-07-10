package 二叉树;

/**
 * https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/
 * https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/solution/bfsjie-jue-zui-hao-de-ji-bai-liao-100de-yong-hu-by/
 */
public class _117_填充每个节点的下一个右侧节点指针_II {
    public Node connect(Node root) {
        Node dummy = new Node();
        dummy.next = root;

        while (dummy.next != null) {
            Node cur = dummy.next;
            Node tail = dummy;
            dummy.next = null;
            while (cur != null) {
                if (cur.left != null) {
                    tail.next = cur.left;
                    tail = tail.next;
                }
                if (cur.right != null) {
                    tail.next = cur.right;
                    tail = tail.next;
                }
                cur = cur.next;
            }
        }
        return root;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
