package 动态规划;

/**
 * https://leetcode-cn.com/problems/unique-binary-search-trees/
 * https://leetcode-cn.com/problems/unique-binary-search-trees/solution/shou-hua-tu-jie-san-chong-xie-fa-dp-di-gui-ji-yi-h/
 */
public class _96_不同的二叉搜索树 {
    public int numTrees(int n) {
        if (n < 2) return 1;
        // 定义 dp[i] ：用连着的 i 个数，所构建出的 BST 种类数
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            // 左子树元素数量从0到i - 1,右子树从i-1到0
            for (int j = 0; j <= i - 1; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }
}
