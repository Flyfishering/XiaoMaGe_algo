package com.mj;

public class Main {

    static void test1() {
        Trie<Integer> trie = new Trie<>();
        trie.add("cat", 1);
        trie.add("dog", 2);
        trie.add("catalog", 3);
        trie.add("cast", 4);
        trie.add("ほのか", 5);
        Asserts.test(trie.size() == 5);
        Asserts.test(trie.startWith("do"));
        Asserts.test(trie.startWith("c"));
        Asserts.test(trie.startWith("ca"));
        Asserts.test(trie.startWith("cat"));
        Asserts.test(trie.startWith("cata"));
        Asserts.test(!trie.startWith("hehe"));
        Asserts.test(trie.get("ほのか") == 5);
        Asserts.test(trie.remove("cat") == 1);
        Asserts.test(trie.remove("catalog") == 3);
        Asserts.test(trie.remove("cast") == 4);
        Asserts.test(trie.size() == 2);
        Asserts.test(trie.startWith("do"));
        Asserts.test(trie.startWith("ほ"));
        Asserts.test(!trie.startWith("c"));
    }

    public static void main(String args[]) {
        test1();
    }
}
