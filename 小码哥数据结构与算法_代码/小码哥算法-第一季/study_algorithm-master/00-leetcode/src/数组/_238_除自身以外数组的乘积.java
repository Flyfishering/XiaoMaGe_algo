package 数组;

/**
 * https://leetcode-cn.com/problems/product-of-array-except-self/
 */
public class _238_除自身以外数组的乘积 {
    // 算出上半三角，下半三角
    // https://leetcode-cn.com/problems/product-of-array-except-self/solution/product-of-array-except-self-shang-san-jiao-xia-sa/
    public int[] productExceptSelf(int[] nums) {
        int[] ans = new int[nums.length];
        ans[0] = 1;
        ans[nums.length - 1] = 1;

        int p = 1, q = 1;
        for (int i = 0; i < nums.length; i++) {
            ans[i] = p;
            p *= nums[i];
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            ans[i] *= q;
            q *= nums[i];
        }
        return ans;
    }

    // 更加朴素的想法，dp。维护两个数组，left和right。分别是，该数左边和右边的乘积。最后乘一下
    // https://leetcode-cn.com/problems/gou-jian-cheng-ji-shu-zu-lcof/solution/mian-shi-ti-66-gou-jian-cheng-ji-shu-zu-biao-ge-fe/
    public int[] productExceptSelf2(int[] nums) {
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];
        left[0] = 1;
        right[nums.length - 1] = 1;

        for (int i = 1; i < nums.length; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }
        for (int i = nums.length - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }

        int[] res = new int[nums.length];

        for (int i = 0; i < res.length; i++) {
            res[i] = left[i] * right[i];
        }
        return res;
    }
}
