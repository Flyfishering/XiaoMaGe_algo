package com.mj.tree;

import java.util.Comparator;

public class RBTree<E> extends BBST<E>{

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RBTree() {
        this(null);
    }

    public RBTree(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * 节点添加后的处理
     * @param node 新添加的节点
     */
    @Override
    protected void afterAdd(Node<E> node) {
        Node<E> parent = node.parent;

        // 若添加的节点的父节点为空，则表示该节点为根节点
        if (parent == null) {
            black(node);
            return;
        }

        // 若父节点是黑色，直接返回不做处理
        if (isBlack(parent)) return;

        // 叔父节点
        Node<E> uncle = parent.sibling();
        // 祖父节点
        Node<E> grand = red(parent.parent);

        // 叔父节点为红色
        if (isRed(uncle)) {
            black(parent);
            black(uncle);
            // 祖父节点染成红色后进行上溢。
            afterAdd(grand);
            return;
        }

        // 叔父节点不是红色
        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // LL
                black(parent);
            } else { // LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else { // R
            if (node.isLeftChild()) { // RL
                black(node);
                rotateRight(parent);
            } else { // RR
                black(parent);
            }
            rotateLeft(grand);
        }
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<>(element, parent);
    }

    @Override
    protected void afterRemove(Node<E> node) {
        // 用以取代node的子节点是红色
        if (isRed(node)) {
            black(node);
            return;
        }

        Node<E> parent = node.parent;
        // 删除的是根节点
        if (parent == null) return;

        // 删除的是黑色叶子节点

        // 先取得兄弟节点，从兄弟节点的颜色，有无红色子节点开始判断
        // 判断被删除的node是左还是右
        boolean left = parent.left == null || node.isLeftChild();
        Node<E> sibling = left ? parent.right : parent.left;

        // 被删除的节点在左或者右，会影响旋转
        if (left) { // 被删除的节点在左边
            // 先处理兄弟节点为红色的情况。通过旋转，会转变为兄弟节点为黑色的情况
            if (isRed(sibling)) { // 兄弟节点为红色的情况
                black(sibling);
                red(parent);
                rotateLeft(parent);
                // 更换兄弟
                sibling = parent.right;
            }
            // 兄弟节点为黑色的情况
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有红色子节点(没法借),父节点向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                // 父节点为黑色的话，重复下溢的过程
                if (parentBlack) {
                    afterRemove(parent);
                }

            } else { // 至少有一个红色子节点(可以借)
                // 兄弟节点的左边是黑色,兄弟要先旋转
                if (isBlack(sibling.right)) {
                    rotateRight(sibling);
                    // 更换兄弟
                    sibling = parent.right;
                }

                color(sibling, colorOf(parent));
                black(parent);
                black(sibling.right);

                rotateRight(parent);
            }
        } else { // 被删除的节点在右边
            // 先处理兄弟节点为红色的情况。通过旋转，会转变为兄弟节点为黑色的情况
            if (isRed(sibling)) { // 兄弟节点为红色的情况
                black(sibling);
                red(parent);
                rotateRight(parent);
                // 更换兄弟
                sibling = parent.left;
            }
            // 兄弟节点为黑色的情况
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有红色子节点(没法借),父节点向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                // 父节点为黑色的话，重复下溢的过程
                if (parentBlack) {
                    afterRemove(parent);
                }

            } else { // 至少有一个红色子节点(可以借)
                // 兄弟节点的左边是黑色,兄弟要先旋转
                if (isBlack(sibling.left)) {
                    rotateLeft(sibling);
                    // 更换兄弟
                    sibling = parent.left;
                }

                color(sibling, colorOf(parent));
                black(parent);
                black(sibling.left);

                rotateRight(parent);
            }
        }
    }

    // 有replacement的，代码更容易理解
//    @Override
//    protected void afterRemove(Node<E> node, Node<E> replacement) {
//        // 若删除的是红色节点，无需做处理，直接返回
//        if (isRed(node)) return;
//
//        // 用以取代node的子节点是红色
//        if (isRed(replacement)) {
//            black(replacement);
//            return;
//        }
//
//        Node<E> parent = node.parent;
//        // 删除的是根节点
//        if (parent == null) return;
//
//        // 删除的是黑色叶子节点
//
//        // 先取得兄弟节点，从兄弟节点的颜色，有无红色子节点开始判断
//        // 判断被删除的node是左还是右
//        boolean left = parent.left == null || node.isLeftChild();
//        Node<E> sibling = left ? parent.right : parent.left;
//
//        // 被删除的节点在左或者右，会影响旋转
//        if (left) { // 被删除的节点在左边
//            // 先处理兄弟节点为红色的情况。通过旋转，会转变为兄弟节点为黑色的情况
//            if (isRed(sibling)) { // 兄弟节点为红色的情况
//                black(sibling);
//                red(parent);
//                rotateLeft(parent);
//                // 更换兄弟
//                sibling = parent.right;
//            }
//            // 兄弟节点为黑色的情况
//            if (isBlack(sibling.left) && isBlack(sibling.right)) {
//                // 兄弟节点没有红色子节点(没法借),父节点向下跟兄弟节点合并
//                boolean parentBlack = isBlack(parent);
//                black(parent);
//                red(sibling);
//                // 父节点为黑色的话，重复下溢的过程
//                if (parentBlack) {
//                    afterRemove(parent, null);
//                }
//
//            } else { // 至少有一个红色子节点(可以借)
//                // 兄弟节点的左边是黑色,兄弟要先旋转
//                if (isBlack(sibling.right)) {
//                    rotateRight(sibling);
//                    // 更换兄弟
//                    sibling = parent.right;
//                }
//
//                color(sibling, colorOf(parent));
//                black(parent);
//                black(sibling.right);
//
//                rotateRight(parent);
//            }
//        } else { // 被删除的节点在右边
//            // 先处理兄弟节点为红色的情况。通过旋转，会转变为兄弟节点为黑色的情况
//           if (isRed(sibling)) { // 兄弟节点为红色的情况
//                black(sibling);
//                red(parent);
//                rotateRight(parent);
//                // 更换兄弟
//               sibling = parent.left;
//            }
//           // 兄弟节点为黑色的情况
//            if (isBlack(sibling.left) && isBlack(sibling.right)) {
//                // 兄弟节点没有红色子节点(没法借),父节点向下跟兄弟节点合并
//                boolean parentBlack = isBlack(parent);
//                black(parent);
//                red(sibling);
//                // 父节点为黑色的话，重复下溢的过程
//                if (parentBlack) {
//                    afterRemove(parent, null);
//                }
//
//            } else { // 至少有一个红色子节点(可以借)
//                // 兄弟节点的左边是黑色,兄弟要先旋转
//                if (isBlack(sibling.left)) {
//                    rotateLeft(sibling);
//                    // 更换兄弟
//                    sibling = parent.left;
//                }
//
//                color(sibling, colorOf(parent));
//                black(parent);
//                black(sibling.left);
//
//                rotateRight(parent);
//            }
//        }
//    }

    // 染色
    private Node<E> color(Node<E> node, boolean color) {
        if (node == null) return null;
        ((RBNode<E>)node).color = color;
        return node;
    }

    private Node<E> red(Node<E> node) {
        return color(node, RED);
    }

    private Node<E> black(Node<E> node) {
        return color(node, BLACK);
    }

    private boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    private boolean isRed(Node<E> node) {
        return colorOf(node) == RED;
    }

    // 返回节点的颜色
    private boolean colorOf(Node<E> node) {
        // 红黑树的空节点，默认黑色
        return ((RBNode<E>)node) == null ? BLACK : ((RBNode<E>)node).color;
    }

    private static class RBNode<E> extends Node<E> {

        boolean color = RED;

        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }
    }
}
