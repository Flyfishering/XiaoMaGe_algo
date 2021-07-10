package 二叉树;

import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/
 */
public class _116_填充每个节点的下一个右侧节点指针 {
    //https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/solution/dong-hua-yan-shi-san-chong-shi-xian-116-tian-chong/
    // 利用常规的层序遍历。空间O(n),时间O(n)
    public Node connect(Node root) {
        if (root == null) return null;
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size - 1; i++) {
                queue.get(i).next = queue.get(i + 1);
            }
            for (int i = 0; i < size; i++) {
                Node node = queue.remove();
                if (node.left != null) {
                    queue.addLast(node.left);
                }
                if (node.right != null) {
                    queue.addLast(node.right);
                }
            }
        }
        return root;
    }

    // 通过父节点找字节点的next
    public Node connect2(Node root) {
        if (root == null) return null;

        Node pre = root;
        while (pre.left != null) {
            Node parent = pre;
            while (parent != null) {
                parent.left.next = parent.right;
                if (parent.next != null) {
                    parent.right.next = parent.next.left;
                }
                parent = parent.next;
            }
            pre = pre.left;
        }

        return root;
    }

    // 通过递归
    public Node connect3(Node root) {
        if (root == null) return null;
        dfs(root);
        return root;
    }

    private void dfs(Node root) {
        if (root == null) return;

        Node left = root.left;
        Node right = root.right;

        while (left != null) {
            left.next = right;
            left = left.right;
            right = right.left;
        }

        dfs(root.left);
        dfs(root.right);
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
    };
}
