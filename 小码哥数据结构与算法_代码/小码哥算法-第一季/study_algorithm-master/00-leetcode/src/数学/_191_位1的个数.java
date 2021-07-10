package 数学;

/**
 * https://leetcode-cn.com/problems/number-of-1-bits/
 */
public class _191_位1的个数 {
    public int hammingWeight(int n) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) {
                ans++;
            }
        }
        return ans;
    }

    public int hammingWeight2(int n) {
        int ans = 0;
        while (n != 0) {
            // n & (n - 1)，每次翻转n二进制位的最后一个1
            n = n & (n - 1);
            ans++;
        }
        return ans;
    }
}
