package com.mj;

import javafx.scene.effect.Bloom;

public class Main {

    public static void main(String[] args) {
        BloomFilter<Integer> bf = new BloomFilter<>(1_00_0000, 0.01);
        for (int i = 1; i <=50 ; i++) {
            bf.put(i);
        }
        for (int i = 1; i <=100 ; i++) {
            System.out.println(bf.contains(i));
        }
    }
}
