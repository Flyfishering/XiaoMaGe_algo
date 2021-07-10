package com.mj;

public class BloomFilter<T> {
    /**
     * 二进制向量的长度(一共有多少个二进制位)
     */
    private int bitSize;

    /**
     * 二进制向量
     */
    private long[] bits;

    /**
     * 哈希函数的个数
     */
    private int hashSize;

    /**
     * @param n 数据规模
     * @param p 误判率，取值范围(0, 1)
     */
    public BloomFilter(int n, double p) {
        if (n <= 0 || p <= 0 || p >= 1) {
            throw new IllegalArgumentException("wrong p or n.");
        }

        double ln2 = Math.log(2);

        bitSize = (int) (- (n * Math.log(p)) / (ln2 * ln2));
        hashSize = (int) (bitSize * ln2 / n);
        bits = new long[(bitSize + Long.SIZE - 1) / Long.SIZE];

        // 每一页有100条数据,pageSize
        // 一共有9999条数据,n
        // 有多少页？pageCount = (n + pageSize - 1) / pageSize
        // ((9999 + 100 - 1) / 100)
    }

    // 添加元素
    public void put(T value) {
        nullCheck(value);

        // 利用value生成两个整数
        int hash1 = value.hashCode();
        int hash2 = hash1 >>> 16;

        for (int i = 0; i < hashSize; i++) {
            int combinedHash = hash1 + (i * hash2);
            if (combinedHash < 0) {
                combinedHash = ~combinedHash;
            }
            // 生成一个二进制位的索引
            int index = combinedHash % bitSize;
            // 设置index位置的二进制位为1
            bits[index / Long.SIZE] |= (long) 1 << (index % Long.SIZE);
        }
    }

    // 包含元素
    public boolean contains(T value) {
        nullCheck(value);
        // 利用value生成两个整数
        int hash1 = value.hashCode();
        int hash2 = hash1 >>> 16;

        for (int i = 0; i < hashSize; i++) {
            int combinedHash = hash1 + (i * hash2);
            if (combinedHash < 0) {
                combinedHash = ~combinedHash;
            }
            // 生成一个二进制位的索引
            int index = combinedHash % bitSize;
            // 查询index位置的二进制位是否为0
            if ((bits[index / Long.SIZE] & (long) 1 << (index % Long.SIZE)) == 0) return false;

        }
        return true;
    }

    /**
     * 设置index位置的二进制位为1
     */
    private void set(int index) {
        // 在long数组中找到对应的long
        long value = bits[index / Long.SIZE];
        bits[index / Long.SIZE] = value | (1L << (index % Long.SIZE));
    }

    /**
     * 查看index位置的二进制位值
     * @return true代表1，false代表0
     */
    private boolean get(int index) {
        // 在long数组中找到对应的long
        long value = bits[index / Long.SIZE];
        value &= (long) 1 << (index % Long.SIZE);
        return value == 1;
    }

    private void nullCheck(T value) {
        if (value == null) {
            throw new IllegalArgumentException("value must not be null");
        }
    }
}
