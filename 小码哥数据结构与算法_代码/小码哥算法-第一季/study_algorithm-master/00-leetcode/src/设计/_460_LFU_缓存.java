package 设计;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/lfu-cache/
 * https://leetcode-cn.com/problems/lfu-cache/solution/lfuhuan-cun-by-leetcode-solution/
 */
public class _460_LFU_缓存 {
    class LFUCache {
        int capacity;
        // 用来存放key-value
        Map<Integer, Node> key_table;
        // 用来存放频次信息
        Map<Integer, LinkedList<Node>> freq_table;
        // 用来存放当前的最小频次
        int minFreq;

        public LFUCache(int capacity) {
            this.capacity = capacity;
            key_table = new HashMap<>();
            freq_table = new HashMap<>();
            minFreq = 0;
        }

        public int get(int key) {
            if (capacity == 0) return -1;
            // 若key不存在，直接返回-1
            if (!key_table.containsKey(key)) return -1;
            // key_table中获取该节点
            Node node = key_table.get(key);
            int freq = node.freq;

            // 从对应的频次链表中，删除该节点
            freq_table.get(freq).remove(node);

            // 若删除该节点后，对应频次的链表空掉了，则从freq_table中删掉该频次
            if (freq_table.get(freq).size() == 0) {
                freq_table.remove(freq);
                // 注意，可能需要更新minFreq
                if (minFreq == freq) {
                    minFreq++;
                }
            }

            // 插入到freq + 1中去
            LinkedList<Node> list = freq_table.getOrDefault(freq + 1, new LinkedList<>());
            // 加到最前面
            node.freq += 1;
            list.addFirst(node);

            freq_table.put(freq + 1, list);
            key_table.put(key, list.peekFirst());

            return node.value;
        }

        public void put(int key, int value) {
            if (capacity == 0) return;
            Node node = key_table.get(key);
            // 当前key对应的元素存在
            if (node != null) {
                // 与get操作几乎一样
                int freq = node.freq;
                freq_table.get(freq).remove(node);
                if (freq_table.get(freq).size() == 0) {
                    freq_table.remove(freq);
                    if (freq == minFreq) {
                        minFreq++;
                    }
                }
                LinkedList<Node> list = freq_table.getOrDefault(freq + 1, new LinkedList<>());
                list.addFirst(node);
                node. value = value;
                node.freq += 1;
                freq_table.put(freq + 1, list);
                key_table.put(key, list.peekFirst());
            } else { // key对应的元素不存在
                // 缓存已满,需要删除一个元素
                if (key_table.size() == capacity) {
                    // 通过minFreq找到需要删除的元素(minFreq对应链表的末尾元素)
                    node = freq_table.get(minFreq).peekLast();
                    // 删掉
                    key_table.remove(node.key);
                    freq_table.get(minFreq).pollLast();
                    // 如果链表中已经没有元素了，从freq_table中删掉
                    if (freq_table.get(minFreq).size() == 0) {
                        freq_table.remove(minFreq);
                    }
                }

                // 新加入的元素，频次必然为1。把它加入两张哈希表
                LinkedList<Node> list = freq_table.getOrDefault(1, new LinkedList<>());
                list.addFirst(new Node(key, value, 1));
                freq_table.put(1, list);
                key_table.put(key, list.peekFirst());

                minFreq = 1;
            }
        }
    }

    class Node {
        int key;
        int value;
        int freq; // 频次

        public Node(int key, int value, int freq) {
            this.key = key;
            this.value = value;
            this.freq = freq;
        }
    }
}

// 两个哈希表
// 一个用来存放key-value,其中value为链表节点地址
// 一个用来存放 频次-对应的频次的链表
