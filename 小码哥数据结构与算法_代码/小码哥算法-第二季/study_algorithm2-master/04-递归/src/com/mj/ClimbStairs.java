package com.mj;

public class ClimbStairs {

    // 优化跟斐波那契一样，只是边界值不同
    int climbStairs(int n) {
        if (n <= 2) return n;
        return climbStairs(n - 1) + climbStairs(n - 2);
    }
}
