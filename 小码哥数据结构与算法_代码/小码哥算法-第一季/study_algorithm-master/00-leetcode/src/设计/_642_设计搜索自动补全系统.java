package 设计;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/design-search-autocomplete-system/
 */
public class _642_设计搜索自动补全系统 {
    class AutocompleteSystem {
        TrieNode root;
        TrieNode cur;
        StringBuilder sb;
        public AutocompleteSystem(String[] sentences, int[] times) {
            root = new TrieNode();
            cur = root;
            sb = new StringBuilder();
            for (int i = 0; i < sentences.length; i++) {
                add(sentences[i], times[i]);
            }
        }

        // 往前缀树中添加句子
        private void add(String sentence, int times) {
            TrieNode tmp = root;
            // 添加句子的时候，需要更新到达该句子的路径上，所有节点的hotList
            // path变量用来记录路径上的所有节点
            List<TrieNode> path = new ArrayList<>();
            for (char c : sentence.toCharArray()) {
                // 由于存在空白字符，需要判断
                int idx = c == ' ' ? 26 : c - 'a';
                // 不存在就new
                if (tmp.children[idx] == null) {
                    tmp.children[idx] = new TrieNode();
                }
                // 往下走
                tmp = tmp.children[idx];
                path.add(tmp);
            }
            tmp.sentence = sentence;
            tmp.times += times;

            // 更新hotList
            for (TrieNode trieNode : path) {
                trieNode.update(tmp);
            }
        }

        public List<String> input(char c) {
            List<String> res = new ArrayList<>();
            if (c == '#') {
                add(sb.toString(), 1);
                sb = new StringBuilder();
                cur = root;
                return res;
            }
            sb.append(c);
            int idx = c == ' ' ? 26 : c - 'a';
            if (cur != null) cur = cur.children[idx];
            if (cur != null) {
                for (TrieNode trieNode : cur.hotList) {
                    res.add(trieNode.sentence);
                }
            }
            return res;
        }
    }

    class TrieNode implements Comparable<TrieNode> {
        String sentence;
        List<TrieNode> hotList;
        int times;
        TrieNode[] children;

        public TrieNode() {
            sentence = null;
            hotList = new ArrayList<>();
            times = 0;
            children = new TrieNode[27];
        }

        @Override
        public int compareTo(TrieNode o) {
            if (o.times == this.times) {
                // o1 - o2,正序
                // 按照asic码顺序排列
                return this.sentence.compareTo(o.sentence);
            } else {
                // o2 - o1,逆序
                // 按照times逆序排列
                return o.times - this.times;
            }
        }

        public void update(TrieNode node) {
            // 不包含才需要加入。包含的话，再加入，就有两个重复的元素了，返回的结果就不对了
            if (!hotList.contains(node)) {
                hotList.add(node);
            }
            Collections.sort(hotList);
            if (hotList.size() > 3) {
                hotList.remove(hotList.size() - 1);
            }
        }
    }
}
