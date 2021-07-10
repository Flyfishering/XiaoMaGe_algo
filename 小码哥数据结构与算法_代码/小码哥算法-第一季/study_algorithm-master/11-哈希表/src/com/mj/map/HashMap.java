package com.mj.map;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class HashMap<K, V> implements Map<K, V> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private int size;
    private Node<K, V>[] table;
    private static final int DEFAULT_CAPACITY = 1 << 4;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    public HashMap() {
        table = new Node[DEFAULT_CAPACITY];
    }

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
        if (size == 0) return;
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
        size = 0;
    }

    /**
     * put
     * @param key key
     * @param value value
     * @return 被覆盖节点的value
     */
    @Override
    public V put(K key, V value) {
        resize();
        int index = index(key);
        // 取出index位置的红黑树根节点
        Node<K, V> root = table[index];
        if (root == null) {
            root = createNode(key, value, null);
            table[index] = root;
            size++;
            fixAfterPut(root);
            return null;
        }

        // 添加新的节点到红黑树(哈希冲突)
        Node<K, V> node = root;
        Node<K, V> parent = null;
        int cmp = 0;
        K k1 = key;
        int h1 = hash(k1);
        Node<K, V> result = null;
        boolean searched = false;
        do {
            parent = node;
            K k2 = node.key;
            int h2 = node.hash;
            if (h1 > h2) {
                cmp = 1;
            } else if (h1 < h2) {
                cmp = -1;
            } else if (Objects.equals(k1, k2)) {
                cmp = 0;
            } else if (k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable
                    && (cmp = ((Comparable) k1).compareTo(k2)) != 0) {
                    // compareTo的结果若为0，则单纯表示两对象的大小相同，但无法表示两对象相同
            } else if (searched) { // 已经进行过了扫描，但是没找到。所以直接比较内存地址就可以了
                cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
            } else { // 能来到这，表示还未扫描。先扫描，排除有重复的元素，再根据内存地址决定大小。
                if ((node.left != null && (result = node(node.left, k1)) != null)
                        || (node.right != null && (result = node(node.right, k1)) != null)) { // 在左,右子树中存在该节点
                    node = result;
                    cmp = 0;
                } else { // 扫描后发现不存在该key，通过内存地址的hash值进行添加
                    searched = true; // 对一棵红黑树的扫描，只需要进行一次
                    cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
                }
            }

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
        } while(node != null);
        Node<K, V> newNode = createNode(key, value, parent);
        if(cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
        fixAfterPut(newNode);
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
        if (size == 0) return false;
        Queue<Node<K, V>> queue = new LinkedList<>();

        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) continue;

            queue.offer(table[i]);
            while (!queue.isEmpty()) {
                Node<K, V> node = queue.poll();
                if (Objects.equals(node.value, value)) {
                    return true;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return false;
    }

    @Override
    public void traversal(Visitor visitor) {
        if (size == 0 || visitor == null) return;
        Queue<Node<K, V>> queue = new LinkedList<>();

        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) continue;
            queue.offer(table[i]);

            while (!queue.isEmpty()) {
                Node<K, V> node = queue.poll();
                if (visitor.visit(node.key, node.value)) return;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
    }

    protected Node<K, V> createNode(K key, V value, Node<K, V> parent) {
        return new Node<>(key, value, parent);
    }

    protected void afterRemove(Node<K, V> willNode, Node<K, V> removedNode) { }

    // 扩容
    private void resize() {
        if ((float)(size / table.length) <= DEFAULT_LOAD_FACTOR) return;
        Node<K, V>[] oldTable = table;
        table = new Node[oldTable.length << 1];

        Queue<Node<K, V>> queue = new LinkedList<>();

        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] == null) continue;

            queue.offer(oldTable[i]);
            while (!queue.isEmpty()) {
                Node<K, V> node = queue.poll();

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                // 挪动代码的时候会清空节点的父左右，所以需要放在入队之后执行
                moveNode(node);
            }
        }
    }

    private void moveNode(Node<K, V> newNode) {
        newNode.parent = null;
        newNode.left = null;
        newNode.right = null;
        newNode.color = RED;
        int index = index(newNode);
        // 取出index位置的红黑树根节点
        Node<K, V> root = table[index];
        if (root == null) {
            root = newNode;
            table[index] = root;
            fixAfterPut(root);
            return;
        }

        // 添加新的节点到红黑树(哈希冲突)
        Node<K, V> node = root;
        Node<K, V> parent = null;
        int cmp = 0;
        K k1 = newNode.key;
        int h1 = newNode.hash;

        do {
            parent = node;
            K k2 = node.key;
            int h2 = node.hash;
            if (h1 > h2) {
                cmp = 1;
            } else if (h1 < h2) {
                cmp = -1;
            } else if (k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable
                    && (cmp = ((Comparable) k1).compareTo(k2)) != 0) {
                // compareTo的结果若为0，则单纯表示两对象的大小相同，但无法表示两对象相同
            } else { // 因为是挪动旧map中的元素到新map中，而旧map中肯定不会存在相等的元素，所以equals跟扫描处理不需要
                    cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
            }

            if(cmp > 0) {
                node = node.right;
            } else if(cmp < 0) {
                node = node.left;
            }
        } while(node != null);

        if(cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        newNode.parent = parent;
        fixAfterPut(newNode);
    }

    // 根据key生成对应的索引(在桶数组中的位置)
    private int index(K key) {
        return hash(key) & (table.length - 1);
    }

    // 扰动计算
    private int hash(K key) {
        if (key == null) return 0;
        int hash = key.hashCode();
        return (hash ^ (hash >>> 16));
    }

    // 根据key生成对应的索引(在桶数组中的位置)
    private int index(Node<K, V> node) {
        return node.hash & (table.length - 1);
    }

    private void fixAfterPut(Node<K, V> node) {
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
            fixAfterPut(grand);
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

    private void fixAfterRemove(Node<K, V> node) {
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
                    fixAfterRemove(parent);
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
                    fixAfterRemove(parent);
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

    // 更新父节点，更新高度
    private void afterRotate(Node<K, V> grand, Node<K, V> parent, Node<K, V> child) {
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else { // 不为左也不为右，表示grand的父节点为空
            table[index(grand)] = parent;
        }

        // child有可能为空
        if (child != null) {
            child.parent = grand;
        }

        grand.parent = parent;

    }

    private Node<K, V> black(Node<K, V> node) {
        return color(node, BLACK);
    }

    // 染色
    private Node<K, V> color(Node<K, V> node, boolean color) {
        if (node == null) return null;
        node.color = color;
        return node;
    }

    // 返回节点的颜色
    private boolean colorOf(Node<K, V> node) {
        // 红黑树的空节点，默认黑色
        return node == null ? BLACK : node.color;
    }

    private boolean isBlack(Node<K, V> node) {
        return colorOf(node) == BLACK;
    }

    private boolean isRed(Node<K, V> node) {
        return colorOf(node) == RED;
    }

    private void keyNotNullCheck(K key) {
        if(key == null) {
            throw new IllegalArgumentException("key must not be null!");
        }
    }

//    // 查找当前元素，返回所在节点
//    private Node<K, V> node(K key) {
//        Node<K, V> node = table[index(key)];
//        int h1 = key == null ? 0 : key.hashCode();
//        while(node != null) {
//            int cmp = compare(key, node.key, h1, node.hash);
//            if (cmp == 0) {
//                return node;
//            } else if (cmp > 0) {
//                node = node.right;
//            } else { // cmp < 0
//                node = node.left;
//            }
//        }
//        return null;
//    }

    // 查找当前key，返回所在节点
    private Node<K, V> node(K k1) {
        Node<K, V> root = table[index(k1)];
        return root == null ? null : node(root, k1);
    }

    // 在当前节点中查找节点(递归)
    private Node<K, V> node(Node<K, V> node ,K k1) {
        int h1 = hash(k1);
        // 存储查找结果
        Node<K, V> result = null;
        int cmp = 0;
        while (node != null) {
            K k2 = node.key;
            int h2 = node.hash;
            // 先比较哈希值，决定往左往右
            if (h1 > h2) {
                node = node.right;
            } else if (h1 < h2) {
                node = node.left;
            } else if (Objects.equals(k1, k2)) {
                return node;
            } else if (k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable
                    && (cmp = ((Comparable) k1).compareTo(k2)) != 0){ // 判断是否是相同类型，是否有可比较性。且cmp为0的时候，无法表示两对象相等
                node = cmp > 0 ? node.right : node.left;
            // 哈希值相等，不具备可比较性(也有可能是空)，也不equals
            } else if (node.right != null && (result = node(node.right, k1)) != null) { // 去右子树找
                return result;
            } else {
                node = node.left; // 进行了优化，减少了递归调用次数
            }
//            else if (node.left != null && (result = node(node.left, k1)) != null) { // 去左子树找
//                return result;
//            } else {
//                return null;
//            }
        }
        return null;
    }

    private Node<K, V> red(Node<K, V> node) {
        return color(node, RED);
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

    /**
     * 比较key的大小
     * @param k1 key1
     * @param k2 key2
     * @param h1 key1的hashcode
     * @param h2 key2的hashcode
     * @return 大小结果
     */
    private int compare(K k1, K k2, int h1, int h2) {
        // 比较hash值
        int result = h1 - h2;
        if (result != 0) return result;

        // 比较equals
        if (Objects.equals(k1, k2)) return 0;

        // hash相等，但是equals不相等
        if (k1 != null && k2 != null
                && k1.getClass() == k2.getClass()
                && k1 instanceof Comparable) {
            return ((Comparable)k1).compareTo(k2);
        }
        // 哈希值相同，同一种类型，equals结果又不想等，且不具备可比较性
        // k1为null,k2不为null
        // k1不为null,k2为null
        // 此时利用内存地址的哈希值直接比较
        return System.identityHashCode(k1) - System.identityHashCode(k2);
    }

    protected V remove(Node<K, V> node) {
        if (node == null) return null;
        Node<K, V> willNode = node;
        V oldValue = node.value;

        if (node.hasTwoChildren()) { // 度为2的节点
            // 找到后继节点
            Node<K, V> successor = successor(node);
            // 用后继节点的值覆盖要删除节点的值
            node.key = successor.key;
            node.value = successor.value;
            node.hash = successor.hash;
            // 删除后继节点(让if外面的代码去删)
            node = successor;
        }

        int index = index(node);

        // 删除node节点(此时节点的度必然是0或者1)
        Node<K, V> replacement = node.left == null ? node.right : node.left;
        // replacement为空，表示node节点度为0
        if (replacement != null) { // 度为1
            // 更改parent
            replacement.parent = node.parent;

            if (node.parent == null) { // node是度为1的节点并且是根节点
                table[index] = replacement;
            } else if (node == node.parent.right) {
                node.parent.right = replacement;
            } else {
                node.parent.left = replacement;
            }

            // 度为2的情况下，真正被删除的是，后继或者前驱节点。
            // 删除节点后的处理
            fixAfterRemove(replacement);
        } else if (node.parent == null) { // node是叶子节点并且是根节点
            table[index] = null;
        } else { // node是叶子节点但不是根节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
            // 度为2的情况下，真正被删除的是，后继或者前驱节点。
            // 删除节点后的处理
            fixAfterRemove(node);
        }

        afterRemove(willNode, node);

        size--;
        return oldValue;
    }
    
    protected static class Node<K, V> {
        K key;
        V value;
        int hash;
        boolean color = RED;
        Node<K, V> left;
        Node<K, V> right;
        Node<K, V> parent;
        public Node(K key, V value, Node<K, V> parent) {
            this.key = key;
            int hash = key == null ? 0 : key.hashCode();
            this.hash = hash ^ (hash >>> 16);
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
