package com.mj.map;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class TreeMap<K, V> implements Map<K, V>{
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    private Comparator<K> comparator; // 比较器

    public TreeMap(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    public TreeMap() {
        this(null);
    }

    protected int size;
    protected Node<K, V> root;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     *
     * @param key 键
     * @param value 值
     * @return 若有重复的键值对，返回被覆盖的键值对的值
     */
    @Override
    public V put(K key, V value) {
        keyNotNullCheck(key);
        // 添加第一个节点
        if(root == null) {
            root = new Node<>(key, value, null);
            size++;

            afterPut(root);
            return null;
        }
        Node<K, V> node = root;
        Node<K, V> parent = null;
        int cmp = 0;
        while(node != null) {
            cmp = compare(key, node.key);
            parent = node;
            if(cmp > 0) {
                node = node.right;
            } else if(cmp < 0) {
                node = node.left;
            } else { // 相等
                node.key = key;
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }
        }
        Node<K, V> newNode = new Node<>(key, value, parent);
        if(cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
        afterPut(newNode);
        return null;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = node(key);
        return node == null ? null : node.value;
    }

    @Override
    public V remove(K key) {
        return remove(node(key));
    }

    @Override
    public boolean containsKey(K key) {
        return node(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        if (root == null) return false;

        Queue<Node<K, V>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<K, V> node = queue.poll();
            if (valEquals(node.value, value)) {
                return true;
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (visitor == null) return;
        traversal(root, visitor);
    }

    private void traversal(Node<K, V> node, Visitor<K, V> visitor) {
        if (node == null || visitor.stop) return;
        traversal(node.left, visitor);
        if (visitor.stop) return;
        visitor.visit(node.key, node.value);
        traversal(node.right, visitor);
    }

    private void keyNotNullCheck(K key) {
        if(key == null) {
            throw new IllegalArgumentException("key must not be null!");
        }
    }

    private void afterPut(Node<K, V> node) {
        Node<K, V> parent = node.parent;

        // 若添加的节点的父节点为空，则表示该节点为根节点
        if (parent == null) {
            black(node);
            return;
        }

        // 若父节点是黑色，直接返回不做处理
        if (isBlack(parent)) return;

        // 叔父节点
        Node<K, V> uncle = parent.sibling();
        // 祖父节点
        Node<K, V> grand = red(parent.parent);

        // 叔父节点为红色
        if (isRed(uncle)) {
            black(parent);
            black(uncle);
            // 祖父节点染成红色后进行上溢。
            afterPut(grand);
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

    // 染色
    private Node<K, V> color(Node<K, V> node, boolean color) {
        if (node == null) return null;
        node.color = color;
        return node;
    }

    private Node<K, V> red(Node<K, V> node) {
        return color(node, RED);
    }

    private Node<K, V> black(Node<K, V> node) {
        return color(node, BLACK);
    }

    private boolean isBlack(Node<K, V> node) {
        return colorOf(node) == BLACK;
    }

    private boolean isRed(Node<K, V> node) {
        return colorOf(node) == RED;
    }

    // 返回节点的颜色
    private boolean colorOf(Node<K, V> node) {
        // 红黑树的空节点，默认黑色
        return node == null ? BLACK : node.color;
    }

    // 左旋转
    private void rotateLeft(Node<K, V> grand) {
        Node<K, V> parent = grand.right;
        Node<K, V> child = parent.left;
        grand.right = child;
        parent.left = grand;

        afterRotate(grand, parent, child);
    }

    // 右旋转
    private void rotateRight(Node<K, V> grand) {
        Node<K, V> parent = grand.left;
        Node<K, V> child = parent.right;

        grand.left = child;
        parent.right = grand;

        afterRotate(grand, parent, child);
    }

    // 更新父节点，更新高度
    private void afterRotate(Node<K, V> grand, Node<K, V> parent, Node<K, V> child) {
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

    private V remove(Node<K, V> node) {
        if (node == null) return null;

        V oldValue = node.value;

        if (node.hasTwoChildren()) { // 度为2的节点
            // 找到后继节点
            Node<K, V> successor = successor(node);
            // 用后继节点的值覆盖要删除节点的值
            node.key = successor.key;
            node.value = successor.value;
            // 删除后继节点(让if外面的代码去删)
            node = successor;
        }

        // 删除node节点(此时节点的度必然是0或者1)
        Node<K, V> replacement = node.left == null ? node.right : node.left;
        // replacement为空，表示node节点度为0
        if (replacement != null) { // 度为1
            // 更改parent
            replacement.parent = node.parent;

            if (node.parent == null) { // node是度为1的节点并且是根节点
                root = replacement;
            } else if (node == node.parent.right) {
                node.parent.right = replacement;
            } else {
                node.parent.left = replacement;
            }

            // 度为2的情况下，真正被删除的是，后继或者前驱节点。
            // 删除节点后的处理
            afterRemove(replacement);
        } else if (node.parent == null) { // node是叶子节点并且是根节点
            root = null;
        } else { // node是叶子节点但不是根节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
            // 度为2的情况下，真正被删除的是，后继或者前驱节点。
            // 删除节点后的处理
            afterRemove(node);
        }

        size--;
        return oldValue;
    }

    /**
     * 找后继结点(中序排序的后一个节点)
     */
    private Node<K, V> successor(Node<K, V> node) {
        if (node == null) return node;
        Node<K, V> pre = node.right;

        // 右子树不为空的时候，后继结点为右子树中的最小节点(最左)。(right.left.left....)
        if(pre != null) {
            while(pre.left != null) {
                pre = pre.left;
            }
            return pre;
        }

        // 从祖父节点中寻找后继结点
        // 当父节点不为空且为父节点的右子树的时候，一直循环(为左子节点的父节点是要找的节点)
        while(node.parent != null && node == node.parent.right) {
            node = node.parent;
        }

        // node.parent == null || node == node.parent.left
        return node.parent;
    }

    private void afterRemove(Node<K, V> node) {
        // 用以取代node的子节点是红色
        if (isRed(node)) {
            black(node);
            return;
        }

        Node<K, V> parent = node.parent;
        // 删除的是根节点
        if (parent == null) return;

        // 删除的是黑色叶子节点

        // 先取得兄弟节点，从兄弟节点的颜色，有无红色子节点开始判断
        // 判断被删除的node是左还是右
        boolean left = parent.left == null || node.isLeftChild();
        Node<K, V> sibling = left ? parent.right : parent.left;

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

    private boolean valEquals(V v1, V v2) {
        return v1 == null ? v2 == null : v1.equals(v2);
    }

    /**
     *
     * @return 返回值等于0，代表e1和e2相等。大于0，代表e1大于e2。小于0，代表e1小于e2
     */
    private int compare(K e1, K e2) {
        // 有比较器的话优先用比较器
        if(comparator != null) {
            return comparator.compare(e1, e2);
        }
        // 没有比较器的话，强转。如果强转失败，表示不可比较，而二叉搜索树的元素，必须可以比较。
        return ((Comparable<K>)e1).compareTo(e2);
    }

    // 查找当前元素，返回所在节点
    private Node<K, V> node(K key) {
        Node<K, V> node = root;
        while(node != null) {
            int cmp = compare(key, node.key);
            if (cmp == 0) {
                return node;
            } else if (cmp > 0) {
                node = node.right;
            } else { // cmp < 0
                node = node.left;
            }
        }
        return null;
    }

    public static class Node<K, V> {
        K key;
        V value;
        boolean color = RED;
        Node<K, V> left;
        Node<K, V> right;
        Node<K, V> parent;
        public Node(K key, V value, Node<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return (left == null && right == null);
        }

        public boolean hasTwoChildren() {
            return (left != null && right != null);
        }

        public boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        public boolean isRightChild() {
            return parent != null && this == parent.right;
        }

        // 返回兄弟节点
        public Node<K, V> sibling() {
            if (isLeftChild()) {
                return parent.right;
            }

            if (isRightChild()) {
                return parent.left;
            }

            // 没有父节点，则没有兄弟节点
            return null;
        }
    }
}
