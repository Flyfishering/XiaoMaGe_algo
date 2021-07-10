package 设计;

import java.util.HashMap;

/**
 * https://leetcode-cn.com/problems/implement-trie-prefix-tree/
 */
public class _208_实现_Trie {
    // 通过数组去实现
    class Trie {
        Node root;
        /** Initialize your data structure here. */
        public Trie() {
            root = new Node();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            Node node = root;
            for (int i = 0; i < word.length(); i++) {
                if (node.nodes[word.charAt(i) - 'a'] == null) {
                    node.nodes[word.charAt(i) - 'a'] = new Node();
                }
                node = node.nodes[word.charAt(i) - 'a'];
            }
            node.isEnd = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            Node node = searchPrefix(word);
            return node != null && node.isEnd;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            return searchPrefix(prefix) != null;
        }

        private Node searchPrefix(String prefix) {
            Node node = root;
            int idx = 0, len = prefix.length();
            while (idx < len) {
                if (node.nodes[prefix.charAt(idx) - 'a'] == null) return null;
                node = node.nodes[prefix.charAt(idx) - 'a'];
                idx++;
            }
            return node;
        }

        private class Node {
            Node[] nodes;
            boolean isEnd;
            public Node() {
                nodes = new Node[26];
            }
        }
    }


    // 哈希表
    class Trie2 {
        Node root;

        /** Initialize your data structure here. */
        public Trie2() {
            root = new Node();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            Node node = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!node.links.containsKey(c)) {
                    node.links.put(c, new Node());
                }
                node = node.links.get(c);
            }
            node.isEnd = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            Node node = searchPrefix(word);
            return node != null && node.isEnd;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            return searchPrefix(prefix) != null;
        }

        private Node searchPrefix(String prefix) {
            Node node = root;
            for (int i = 0; i < prefix.length(); i++) {
                if (node.links.containsKey(prefix.charAt(i))) {
                    node = node.links.get(prefix.charAt(i));
                } else {
                    return null;
                }
            }
            return node;
        }

        private class Node {
            HashMap<Character, Node> links;
            boolean isEnd;
            public Node() {
                links = new HashMap<>();
            }
        }
    }
}
