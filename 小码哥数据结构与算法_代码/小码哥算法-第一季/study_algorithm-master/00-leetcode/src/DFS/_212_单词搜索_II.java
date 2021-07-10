package DFS;

import java.util.*;

// 前缀树节点
class TrieNode {
    Map<Character, TrieNode> children;
    String word;
    public TrieNode() {
        children = new HashMap<>();
    }
}

/**
 * https://leetcode-cn.com/problems/word-search-ii/
 */
public class _212_单词搜索_II {
    // trie + dfs(思路参考官方题解)
    public List<String> findWords(char[][] board, String[] words) {
        List<String> list = new ArrayList<>();
        TrieNode root = new TrieNode();
        // 将words加入前缀树中
        for (String word : words) {
            TrieNode node = root;
            for (Character letter : word.toCharArray()) {
                if (!node.children.containsKey(letter)) {
                    node.children.put(letter, new TrieNode());
                }
                node = node.children.get(letter);
            }
            node.word = word;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 前缀树中包含这个单词开头的时候，顺着前缀树进行深度搜素
                if (root.children.containsKey(board[i][j])) {
                    dfs(board, root, i, j, list);
                }
            }
        }
        return list;
    }

    // 沿着 Trie 的节点回溯。
    // 顺着前缀树去深搜!!只有在前缀树中存在的时候，才会进行深度搜索
    private void dfs(char[][] board, TrieNode parent, int i, int j, List<String> list) {
        char letter = board[i][j];
        TrieNode node = parent.children.get(letter);
        if (node.word != null) {
            list.add(node.word);
            node.word = null;
        }
        board[i][j] = '#';

        if (inArea(board, i - 1, j) && node.children.containsKey(board[i - 1][j])) {
            dfs(board, node, i - 1, j, list);
        }
        if (inArea(board, i + 1, j) && node.children.containsKey(board[i + 1][j])) {
            dfs(board, node, i + 1, j, list);
        }
        if (inArea(board, i, j - 1) && node.children.containsKey(board[i][j - 1])) {
            dfs(board, node, i, j - 1, list);
        }
        if (inArea(board, i, j + 1) && node.children.containsKey(board[i][j + 1])) {
            dfs(board, node, i, j + 1, list);
        }
        board[i][j] = letter;

        // 在回溯过程中逐渐剪除 Trie 中的节点（剪枝）
        // 剪枝！！若当前节点是叶子节点，那么将这个叶子节点剪掉。
        // 这个做法可以极大的节约时间。因为这个深搜是顺着Trie树进行搜索的。
        // 树上的节点越少，需要进行的深搜也就越少！
        // 并且这边剪掉叶子节点完全没有问题。因为能来到叶子节点，
        // 则这个叶子节点上面的单词肯定已经添加到结果集了。
        // 又由于是叶子，所以不可能有后续的结果了！！！
        if (node.children.isEmpty()) {
            parent.children.remove(letter);
        }
    }

    private boolean inArea(char[][] board, int i, int j) {
        return i >= 0 && i < board.length && j >= 0 && j < board[0].length;
    }
}