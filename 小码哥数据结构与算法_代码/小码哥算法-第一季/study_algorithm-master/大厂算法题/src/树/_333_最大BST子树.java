package 树;

import common.TreeNode;

/**
 * https://leetcode-cn.com/problems/largest-bst-subtree/
 */
public class _333_最大BST子树 {
    public int largestBSTSubtree(TreeNode root) {
        return root == null ? 0 : getInfo(root).size;
    }

    // 返回以root为根节点的二叉树的最大BTS子树信息(后序遍历，自底向上)
    private Info getInfo(TreeNode root) {
        if (root == null) return null;
        Info li = getInfo(root.left);
        Info ri = getInfo(root.right);

        /*
        有4种情况，以root为根节点的二叉树就是一棵BST，其最大BST子树就是自身
        ① li != null && ri != null
          && li.root == root.left && root.val > li.max
          && ri.root == root.right && root.val < ri.min
        ② li != null && ri == null
          && li.root == root.left && root.val > li.max
        ③ li == null && ri != null
          && ri.root == root.right && root.val < ri.min
        ④ li == null && ri == null
         */
        int leftBstSize = -1, rightBstSize = -1, max = root.val, min = root.val;
        if (li == null) {
            leftBstSize = 0;
        } else if (li.root == root.left && root.val > li.max){
            leftBstSize = li.size;
            min = li.min;
        }
        if (ri == null) {
            rightBstSize = 0;
        } else if (ri.root == root.right && root.val < ri.min){
            rightBstSize = ri.size;
            max = ri.max;
        }

        // leftBstSize与rightBstSize均大于等于0，表示以root为根节点的这棵树就是一棵BST
        if (leftBstSize >= 0 && rightBstSize >= 0) {
            return new Info(root, leftBstSize + rightBstSize + 1, max, min);
        }
        // 以root为根节点的二叉树，并不是一棵BST
        // 如果左右子树都不为空，返回节点数多的那棵BST
        if (li != null && ri != null) {
            return li.size > ri.size ? li : ri;
        }
        // 一边为空一边不为空，返回不为空的那一方
        return li == null ? ri : li;
    }

    class Info {
        TreeNode root;
        int size;
        int max;
        int min;
        public Info(TreeNode root, int size, int max, int min) {
            this.root = root;
            this.size = size;
            this.max = max;
            this.min = min;
        }
    }

}
