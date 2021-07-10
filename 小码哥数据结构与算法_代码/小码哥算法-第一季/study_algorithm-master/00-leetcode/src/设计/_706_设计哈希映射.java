package 设计;

/**
 * https://leetcode-cn.com/problems/design-hashmap/
 */
public class _706_设计哈希映射 {
    class MyHashMap {
        Node[] nodes;


        /** Initialize your data structure here. */
        public MyHashMap() {
            nodes = new Node[1009];
        }

        /** value will always be non-negative. */
        public void put(int key, int value) {
            int index = key % nodes.length;
            Node node = null;
            Node prev = null;
            if (nodes[index] != null) {
                node = nodes[index];
                while (node != null) {
                    prev = node;
                    if (node.key == key) {
                        node.value = value;
                        return;
                    }
                    node = node.next;
                }
            }
            node = new Node(key, value);
            if (prev != null) {
                prev.next = node;
            } else {
                nodes[index] = node;
            }
        }

        /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
        public int get(int key) {
            int index = key % nodes.length;
            Node node = nodes[index];
            while (node != null) {
                if (node.key == key) return node.value;
                node = node.next;
            }
            return -1;
        }

        /** Removes the mapping of the specified value key if this map contains a mapping for the key */
        public void remove(int key) {
            int index = key % nodes.length;
            Node node = nodes[index];
            Node prev = null;
            while (node != null) {
                if (node.key == key) {
                    if (prev != null) {
                        prev.next = node.next;
                        node.next = null;
                    } else {
                        nodes[index] = node.next;
                    }
                    return;
                }
                prev = node;
                node = node.next;
            }
        }

        class Node {
            int key;
            int value;
            Node next;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }

            public Node() {}
        }
    }
}
