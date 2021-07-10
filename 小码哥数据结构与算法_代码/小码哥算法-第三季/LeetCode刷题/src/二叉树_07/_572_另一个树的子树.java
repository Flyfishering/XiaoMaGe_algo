package 二叉树_07;

/*
* https://leetcode-cn.com/problems/subtree-of-another-tree/
* 该例子的解法是用后续遍历进行序列化操作
* 其实本题不管用什么遍历(层序除外),都可以解决
* 但是如果是前序遍历,有一个坑
*   比如:一个树是12 一个树是2
*   12序列化之后是 12!#!#!
*   2 序列化之后是  2!#!#!
*  很明显2不是12的子树,但通过字符串序列化之后就判断是
*  所以如果是前序遍历,字符串初始化时应该加一个!号
*  即 !12!#!#! 和 !2!#!#!  这样两个字符串再比较就没问题了
* */
public class _572_另一个树的子树 extends _00_baseTree {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null || t == null) return false;
        return postSerialize(s).contains(postSerialize(t));
    }

    public String postSerialize(TreeNode root) {
        StringBuilder sb = new StringBuilder("");
        postSerialize(root,sb);
        return sb.toString();
    }

    public void postSerialize(TreeNode node,StringBuilder sb) {
        if (node.left == null) {
            sb.append("#!");
        } else {
            postSerialize(node.left,sb);
        }
        if (node.right == null) {
            sb.append("#!");
        } else {
            postSerialize(node.right,sb);
        }
        sb.append(node.val).append("!");
    }
}
