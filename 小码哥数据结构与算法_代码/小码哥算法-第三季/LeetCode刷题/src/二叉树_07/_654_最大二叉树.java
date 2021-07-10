package 二叉树_07;

import java.util.Arrays;
import java.util.Stack;

/*
* https://leetcode-cn.com/problems/maximum-binary-tree/
* */
public class _654_最大二叉树 extends _00_baseTree {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        return findRoot(nums,0,nums.length);
    }

    public TreeNode findRoot(int[] nums,int begin,int end) {
        if (begin == end) return null;

        // 找出[begin,end)范围内的最大索引值
        int maxIdx = begin;
        for (int i = begin + 1; i < end; i++) {
            if (nums[maxIdx] < nums[i]) maxIdx = i;

        }
        TreeNode root = new TreeNode(nums[maxIdx]);
        root.left = findRoot(nums,begin,maxIdx);
        root.right = findRoot(nums,maxIdx+1,end);
        return root;
    }

    /*
    * 返回一个数组，数组里面存着每个节点的父节点的索引(如果没有父节点，就存-1)
    * 该题的意思就是,经过上边的算法,我们组成了一个最大的二叉树
    * 请你给你每个节点的父节点的索引
    * 比如3的父节点是6,6的索引是3
    *    2的父节点是3,3的索引是0
    * 就这么依次下去,数组存的值应该是 3 0 1 -1 5 3
    * 解题思路分析:
    *   既然是最大二叉树,取任意一个值,向他的左边或者右边依次去找第一个比他大的值
    *   然后min(左边第一个比他大的,右边第一个比他大的)
    *   但问题就是如何找他左边第一个比他大和右边第一个比他大的值
    *   这时就需要掌握一个技巧,使用栈
    * */
    public int[] parentIndexes(int[] nums) {
        int[] lis = new int[nums.length];
        int[] ris = new int[nums.length];
        int[] res = new int[nums.length];
        // 初始化
        for (int i = 0; i < nums.length; i++) {
            ris[i] = -1;
            lis[i] = -1;
        }
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < nums.length ; i++) {
            // 如果栈不为空,并且栈顶的元素比当前遍历到的值要小
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                // 说明当前遍历到的值,是栈顶元素右边第一个比他大的值
                int idx = stack.pop(); // 取出栈顶元素
                ris[idx] = i; // 设定栈顶元素的右边第一个比他大的值索引
            }
            if (!stack.isEmpty()) {
                lis[i] = stack.peek();
            }
            stack.push(i);
        }
        //剩下的操作就是从lis,ris中分别找对应元素的最小值
        // 为什么要找最小值,因为lis,ris中存的是一个元素左边的第一个最大值,右边的第一个最大值
        // 既然是最大二叉树,肯定你左右两边那个相对大的那个值一定是别的的父节点,可能是自己的祖父节点
        for (int i = 0; i < res.length; i++) {
            if (lis[i] == -1 && ris[i] == -1) {
                res[i] = -1; // 将根节点的值设置为-1
                continue;
            }
            if (lis[i] == -1) {
                res[i] = ris[i];
            } else if (ris[i] == -1) {
                res[i] = lis[i];
            } else if (nums[lis[i]] < nums[ris[i]]) {
                res[i] = lis[i];
            } else {
                res[i] = ris[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        _654_最大二叉树 o = new _654_最大二叉树();
        int[] nums = { 3, 2, 1, 6, 0, 5 };
        System.out.println(Arrays.toString(o.parentIndexes(nums)));
    }
}
