package com.mj.tree;

import java.util.Comparator;

public class BBST<E> extends BinarySearchTree<E>{

    public BBST() {
        this(null);
    }

    public BBST(Comparator<E> comparator) {
        super(comparator);
    }

    // 统一旋转
    protected void rotate(
            Node<E> r, // 子树的根节点
            Node<E> a, Node<E> b, Node<E> c,
            Node<E> d,
            Node<E> e, Node<E> f, Node<E> g) {
        // 让d成为这颗子树的根节点
        d.parent = r.parent;
        if (r.isLeftChild()) {
            d.parent.left = d;
        } else if (r.isRightChild()) {
            d.parent.right = d;
        } else {
            root = d;
        }

        // a-b-c
        b.left = a;
        b.right = c;

        if (a != null) a.parent = b;
        if (c != null) c.parent = b;

        // e-f-g
        f.left = e;
        f.right = g;

        if (e != null) {
            e.parent = f;
        }
        if (g != null) {
            g.parent = f;
        }

        // b-d-f
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;

    }

    // 左旋转
    protected void rotateLeft(Node<E> grand) {
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;

        afterRotate(grand, parent, child);
    }

    // 右旋转
    protected void rotateRight(Node<E> grand) {
        Node<E> parent = grand.left;
        Node<E> child = parent.right;

        grand.left = child;
        parent.right = grand;

        afterRotate(grand, parent, child);
    }

    // 更新父节点，更新高度
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else { // 不为左也不为右，表示grand的父节点为空
            root = parent;
        }

        // child有可能为空
        if (child != null) {
            child.parent = grand;
        }

        grand.parent = parent;

    }
}
