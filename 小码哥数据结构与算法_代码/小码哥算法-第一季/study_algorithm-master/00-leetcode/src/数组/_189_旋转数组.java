package 数组;

/**
 * https://leetcode-cn.com/problems/rotate-array/
 */
public class _189_旋转数组 {
    // 开辟额外空间会很简单,时间，空间均为O(n)。空间可以优化为O(k)或者O(n-k)
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        int[] res = new int[len];
        for (int i = 0; i < nums.length; i++) {
            res[(i + k) % len] = nums[i];
        }
        System.arraycopy(res, 0, nums, 0, nums.length);
    }

    // 类似插入排序  (超时。。。)
    public void rotate2(int[] nums, int k) {
        k = k % nums.length;
        int start = nums.length - k;
        for (int i = start; i < nums.length; i++) {
            int val = nums[i];
            for (int j = i - 1; j >= i - start; j--) {
                nums[j + 1] = nums[j];
            }
            nums[i - start] = val;
        }
    }

    // 时间O(kn),模拟旋转的过程,一次一次的旋转,竟然也超时了。。
    public void rotate3(int[] nums, int k) {
        int lastIdx = nums.length - 1;
        for (int i = 0; i < k; i++) {
            int last = nums[lastIdx];
            for (int j = nums.length - 1; j > 0; j--) {
                nums[j] = nums[j - 1];
            }
            nums[0] = last;
        }
    }

    // 三次翻转。。
    /*
    nums   = "----->-->"; k =3
    result = "-->----->";

    reverse "----->-->" we can get "<--<-----"
    reverse "<--" we can get "--><-----"
    reverse "<-----" we can get "-->----->"
     */
    public void rotate4(int[] nums, int k) {
        int len = nums.length;
        k %= len;
        reverse(nums, 0, len - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, len - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }

    // 环形旋转
    // https://leetcode-cn.com/problems/rotate-array/solution/xuan-zhuan-shu-zu-yuan-di-huan-wei-xiang-xi-tu-jie/
    public void rotate5(int[] nums, int k) {
        int len = nums.length;
        int count = 0;
        k %= len;
        for (int start = 0; count < len; start++) {
            int cur = start;
            int pre = nums[cur];
            do {
                int next = (cur + k) % len;
                int tmp = nums[next];
                nums[next] = pre;
                pre = tmp;
                cur = next;
                count++;
            } while (start != cur);
        }
    }
}
