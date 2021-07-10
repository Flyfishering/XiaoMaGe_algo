package 图;

import java.util.*;

public class _721_账户合并 {
    // https://leetcode-cn.com/problems/accounts-merge/solution/tu-jie-yi-ran-shi-bing-cha-ji-by-yexiso-5ncf/
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // 1.构建并查集,并将"Email"和对应的"id"存储起来
        Map<String, Integer> emailToId = new HashMap<>();
        int n = accounts.size();
        UnionFind uf = new UnionFind(n);

        for (int i = 0; i < n; i++) {
            int len = accounts.get(i).size();
            for (int j = 1; j < len; j++) {
                String email = accounts.get(i).get(j);
                if (emailToId.containsKey(email)) {
                    // 再次找到相同的email,就将两次的id通过并查集进行合并
                    uf.union(i, emailToId.get(email));
                } else {
                    // 如果当前email是第一次找到,加入map
                    emailToId.put(email, i);
                }
            }
        }

        // 2.将"id"与对应的"Email"组合到一个集合<id, e1, e2...>
        Map<Integer, List<String>> idToEmails = new HashMap<>();
        // 遍历每个email(map已经自动去重)
        for (Map.Entry<String, Integer> emailIdEntry : emailToId.entrySet()) {
            int id = uf.find(emailIdEntry.getValue());
            List<String> list = idToEmails.getOrDefault(id, new ArrayList<>());
            list.add(emailIdEntry.getKey());
            idToEmails.put(id, list);
        }

        // 3.将"id"换成相应的"name"
        List<List<String>> ans = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> idEmailsEntry : idToEmails.entrySet()) {
            List<String> tmp = new ArrayList<>();
            List<String> emails = idEmailsEntry.getValue();
            // 添加对应的name
            tmp.add(accounts.get(idEmailsEntry.getKey()).get(0));
            Collections.sort(emails);
            tmp.addAll(emails);
            ans.add(tmp);
        }
        return ans;
    }

    class UnionFind {
        int[] parent;
        int[] rank;

        public UnionFind(int capacity) {
            parent = new int[capacity];
            rank = new int[capacity];

            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
            }
            Arrays.fill(rank, 1);
        }

        // Path Compression
        public int find(int v) {
            if (v != parent[v]) {
                parent[v] = find(parent[v]);
            }
            return parent[v];
        }

        public void union(int v1, int v2) {
            int p1 = find(v1);
            int p2 = find(v2);
            if (p1 == p2) return;
            if (rank[p1] < rank[p2]) {
                parent[p1] = p2;
            } else if (rank[p1] > rank[p2]) {
                parent[p2] = p1;
            } else {
                parent[p1] = p2;
                rank[p2]++;
            }
        }
    }
}
