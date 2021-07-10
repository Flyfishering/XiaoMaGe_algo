package 二叉树;

/**
 * https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
 */
public class _114_二叉树展开为链表 {
    // 递归(前序遍历)
    public void flatten(TreeNode root) {
        if (root == null) return;

        // 左边不为空才需要把左边往右边挪
        if (root.left != null) {
            TreeNode oldRight = root.right;
            root.right = root.left;
            root.left = null;

            TreeNode rightest = root;
            while (rightest.right != null) {
                rightest = rightest.right;
            }

            rightest.right = oldRight;
        }

        flatten(root.right);
    }

    // 迭代
    public void flatten2(TreeNode root) {
        while (root != null) {
            if (root.left != null) {
                TreeNode oldRight = root.right;
                root.right = root.left;
                root.left = null;

                TreeNode rightest = root;
                while (rightest.right != null) {
                    rightest = rightest.right;
                }
                rightest.right = oldRight;
            }
            root = root.right;
        }
    }

    TreeNode prev;
    // 递归(后序遍历)
    public void flatten3(TreeNode root) {
        if (root == null) return;
        flatten3(root.right);
        flatten3(root.left);

        root.right = prev;
        root.left = null;
        prev = root;
    }
}
