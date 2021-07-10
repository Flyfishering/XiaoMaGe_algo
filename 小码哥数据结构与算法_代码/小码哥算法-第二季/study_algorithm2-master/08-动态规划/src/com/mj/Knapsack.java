package com.mj;

// 0-1背包
public class Knapsack {

    // 恰好装满
    static int maxValueExactly(int[] values, int[] weights, int capacity) {
        if (values == null || values.length == 0) return 0;
        if (weights == null || weights.length == 0) return 0;
        if (values.length != weights.length || capacity <= 0) return 0;

        int[] dp = new int[capacity + 1];

        // 初期值
        for (int j = 1; j <= values.length; j++) {
            dp[j] = Integer.MIN_VALUE;
        }

        // dp[0][j],dp[i][0]的默认值为0
        for (int i = 1; i <= values.length; i++) {
            // for (int j = capacity; j >= 1; j--) {
            // j的下界优化为weights[i - 1]，因为只有j >= weights[i - 1]的时候才会对dp[j]赋值
            for (int j = capacity; j >= weights[i - 1]; j--) {
                dp[j] = Math.max(dp[j], values[i - 1] + dp[j - weights[i - 1]]);
            }
        }
        return dp[capacity] < 0 ? -1 : dp[capacity];
    }

    // 优化至一维数组
    static int maxValue(int[] values, int[] weights, int capacity) {
        if (values == null || values.length == 0) return 0;
        if (weights == null || weights.length == 0) return 0;
        if (values.length != weights.length || capacity <= 0) return 0;

        int[] dp = new int[capacity + 1];

        // dp[0][j],dp[i][0]的默认值为0
        for (int i = 1; i <= values.length; i++) {
            // for (int j = capacity; j >= 1; j--) {
            // j的下界优化为weights[i - 1]，因为只有j >= weights[i - 1]的时候才会对dp[j]赋值
            for (int j = capacity; j >= weights[i - 1]; j--) {
                dp[j] = Math.max(dp[j], values[i - 1] + dp[j - weights[i - 1]]);
            }
        }
        return dp[capacity];
    }

    // dp(i, j) 可以选择前i个物品，最大承重为j
    // 如果j < weights[i-1],dp(i, j) = dp(i-1, j)
    // 如果不选择第i件物品,dp(i, j) = dp(i-1, j)
    // 如果选择第i件物品,dp(i, j) = values[i-1] + dp(i-1, j-weights[i-1])
    // dp(i, j) = max(...)
    static int maxValue1(int[] values, int[] weights, int capacity) {
        if (values == null || values.length == 0) return 0;
        if (weights == null || weights.length == 0) return 0;
        if (values.length != weights.length || capacity <= 0) return 0;

        // 可选前i件物品，最大承重为capacity的情况下，最大的价值
        int[][] dp = new int[values.length + 1][capacity + 1];

        // dp[0][j],dp[i][0]的默认值为0
        for (int i = 1; i <= values.length; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (j < weights[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], values[i - 1] + dp[i - 1][j - weights[i - 1]]);
                }
            }
        }
        return dp[values.length][capacity];
    }

    public static void main(String[] args) {
        int[] values = {6, 3, 5, 4, 6};
        int[] weights = {2, 2, 6, 5, 4};
        int capacity = 10;
        System.out.println(maxValue(values, weights, capacity));
    }
}
