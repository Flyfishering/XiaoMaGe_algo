package 高频题;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/lru-cache/
 */
public class _146_LRU_缓存机制 {
    class LRUCache {
        // 虚拟头节点
        private Node first;
        // 虚拟尾节点
        private Node last;
        Map<Integer, Node> map;
        int capacity;

        public LRUCache(int capacity) {
            map = new HashMap<>(capacity);
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
            // 值存在，单纯更新下值
            if (node != null) {
                node.value = value;
                removeNode(node);
            } else { // 添加一对新的key-value
                if (map.size() == capacity) {
                    // 淘汰最少使用的key-value
                    removeNode(map.remove(last.prev.key));
                }
                node = new Node(key, value);
                map.put(key, node);
            }
            addAfterFirst(node);
        }

        // 从双向链表中删除node节点
        private void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        // 将node节点插入到first节点后面
        private void addAfterFirst(Node node) {
            // node与first.next接线
            node.next = first.next;
            node.next.prev = node;

            // node与first接线
            first.next = node;
            node.prev = first;
        }

        class Node {
            int key;
            int value;
            Node next;
            Node prev;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
            public Node() {}
        }
    }
}
