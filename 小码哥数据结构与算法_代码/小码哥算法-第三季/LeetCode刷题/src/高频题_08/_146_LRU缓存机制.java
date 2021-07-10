package 高频题_08;

import sun.jvm.hotspot.utilities.Assert;

import java.util.HashMap;

/*
* https://leetcode-cn.com/problems/lru-cache/
* */
public class _146_LRU缓存机制 {
    HashMap<Integer,Node> map;
    int capacity;
    Node first;
    Node last;

    public _146_LRU缓存机制(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
        first = new Node();
        last = new Node();

        first.next = last;
        last.prev = first;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;
        removeNode(node);
        addAfterFirst(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node != null) {     // 之前存在该值
            node.value = value; // 更新value
            removeNode(node);   // 移除该节点在链表中目前的位置,然后移动到最前边
        } else { // 如果不存在
            if (map.size() == capacity) { // 如果此次容器已经满了
                removeNode(map.remove(last.prev.key)); // 移除hash表中的值,和双向链表中的最后一个值
            }
            node = new Node(key,value);
            map.put(key,node);
        }
        addAfterFirst(node);
    }

    /*
    *  将node节点插入到first节点的后面
    * */
    private void addAfterFirst(Node node) {
        node.next = first.next;
        first.next.prev = node;

        first.next = node;
        node.prev = first;
    }

    /*
    * 从双向链表中删除node节点
    * */
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /*
    * 节点类
    * */
    private static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
        public Node() {}
    }

    /*
    * 一下是测试使用代码
    * */
    private static class Asserts {
        public static void test(boolean v) {
            if (v) return;
            System.err.println(new RuntimeException().getStackTrace()[1]);
        }
    }
    public static void main(String[] args) {
        _146_LRU缓存机制 cache = new _146_LRU缓存机制( 2 /* 缓存容量 */ );
        cache.put(1, 1);
        cache.put(2, 2);
        Asserts.test(cache.get(1) == 1); // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        Asserts.test(cache.get(2) == -1);       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        Asserts.test(cache.get(1) == -1);       // 返回 -1 (未找到)
        Asserts.test(cache.get(3) == 3);       // 返回  3
        Asserts.test(cache.get(4) == 4);       // 返回  4
    }
}


