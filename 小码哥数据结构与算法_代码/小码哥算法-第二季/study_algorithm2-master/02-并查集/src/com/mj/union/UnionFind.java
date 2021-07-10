package com.mj.union;

public abstract class UnionFind {
    // 存放父节点的数组。下标为元素，值为该元素的父节点
    // Quick Find中，父节点同时也是根节点。
    // 索引跟值相同时，可认定该元素是根节点
    protected int[] parents;

    public UnionFind(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("capacity must be >=1 ");
        }
        parents = new int[capacity];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
    }

    /**
     * 查找v所属的集合(根节点)
     * @param v 要查找的元素
     * @return 所属集合(根节点)
     */
    public abstract int find(int v);

    // 检查v1,v2是否属于同一个集合
    public boolean isSame(int v1, int v2) {
        return find(v1) == find(v2);
    }

    // 合并v1, v2所在的集合
    public abstract void union(int v1, int v2);

    // 范围检查(防止数组越界)
    protected void rangeCheck(int v) {
        if (v < 0 || v >= parents.length) {
            throw new IllegalArgumentException("v is out of bounds");
        }
    }
}
