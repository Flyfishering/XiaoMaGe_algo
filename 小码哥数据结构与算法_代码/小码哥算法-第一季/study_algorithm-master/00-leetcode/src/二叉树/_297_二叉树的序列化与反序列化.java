package 二叉树;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
 * https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/solution/297-er-cha-shu-de-xu-lie-hua-yu-fan-xu-l-647c/
 */
public class _297_二叉树的序列化与反序列化 {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        postSerialize(root, sb);
        return sb.toString();
    }

    // 通过后序遍历进行序列化
    private void postSerialize(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(",#");
            return;
        }
        postSerialize(node.left, sb);
        postSerialize(node.right, sb);
        sb.append(",").append(node.val);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Stack<String> stack = new Stack<>();

        for (String datum : data.split(",")) {
            stack.push(datum);
        }

        TreeNode root = postDeserialize(stack);

        return root;
    }

    // 对后序遍历序列化的结果进行反序列化
    private TreeNode postDeserialize(Stack<String> stack) {
        String str = stack.pop();
        if (str.equals("#")) return null;
        TreeNode node = new TreeNode(Integer.parseInt(str));
        node.right = postDeserialize(stack);
        node.left = postDeserialize(stack);
        return node;
    }

    // 通过前序遍历完成
    public String serialize2(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preSerialize(root, sb);
        return sb.toString();
    }

    private void preSerialize(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("#,");
            return;
        }
        sb.append(node.val).append(',');
        preSerialize(node.left, sb);
        preSerialize(node.right, sb);
    }


    // 对前序遍历的结果进行反序列化
    public TreeNode deserialize2(String data) {
        return preDeserialize(new LinkedList(Arrays.asList(data.split(","))));
    }

    private TreeNode preDeserialize(Queue<String> queue) {
        String str = queue.poll();
        if (str.equals("#")) return null;
        TreeNode node = new TreeNode(Integer.parseInt(str));
        node.left = preDeserialize(queue);
        node.right = preDeserialize(queue);
        return node;
    }

    // 通过层序遍历来序列化
    public String serialize3(TreeNode root) {
        if (root == null) return "";
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        StringBuilder sb = new StringBuilder();

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node != null) {
                sb.append(node.val).append(",");
                queue.offer(node.left);
                queue.offer(node.right);
            } else {
                sb.append("null,");
            }
        }
        return sb.toString();
    }

    // 使用层序遍历来反序列化
    public TreeNode deserialize3(String data) {
        if (data == null || data.length() == 0) return null;
        String[] dataArray = data.split(",");
        if (dataArray[0].equals("null")) return null;
        TreeNode root = new TreeNode(Integer.parseInt(dataArray[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int idx = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (!dataArray[idx].equals("null")) {
                node.left = new TreeNode(Integer.parseInt(dataArray[idx]));
                queue.offer(node.left);
            }
            idx++;
            if (!dataArray[idx].equals("null")) {
                node.right = new TreeNode(Integer.parseInt(dataArray[idx]));
                queue.offer(node.right);
            }
            idx++;
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);

        root.left = node2;
        root.right = node3;
        node3.left = node4;
        node3.right = node5;

        _297_二叉树的序列化与反序列化 test = new _297_二叉树的序列化与反序列化();
        System.out.println(test.serialize3(root));
    }
}
