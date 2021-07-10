package 高频题;

/**
 * https://leetcode-cn.com/problems/container-with-most-water/
 */
public class _11_盛最多水的容器 {
    // 数组，双指针，贪心
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1, water = 0;

        while (l < r) {
            if (height[l] <= height[r]) { // 左边界的柱子比较小的话，左边边界++
                water = Math.max(water, height[l] * (r - l));
                l++;
            } else { // 右边界的柱子比较小的话，右边界--
                water = Math.max(water, height[r] * (r - l));
                r--;
            }
        }

        return water;
    }

    // 代码量的优化
    public int maxArea2(int[] height) {
        int l = 0, r = height.length - 1, water = 0;

        while (l < r) {
            int minH = height[l] < height[r] ? height[l++] : height[r--];
            water = Math.max(water, minH * (r - l + 1));
        }

        return water;
    }

    // 减少了一些不必要的计算，速度会快一些(思路可以参考三数之和的去重处理)
    public int maxArea3(int[] height) {
        int l = 0, r = height.length - 1, water = 0;

        while (l < r) {
            if (height[l] <= height[r]) { // 左边界的柱子比较小的话，左边边界++
                int minH = height[l];
                water = Math.max(water, height[l] * (r - l));
                l++;
                // 如果移动后的柱子高度 <= 原先的高度，那么跳过
                while (l < r && height[l] <= minH) l++;
            } else { // 右边界的柱子比较小的话，右边界--
                int minH = height[r];
                water = Math.max(water, height[r] * (r - l));
                r--;
                // 如果移动后的柱子高度 <= 原先的高度，那么跳过
                while (l < r && height[r] <= minH) r--;
            }
        }

        return water;
    }

    // 另一种优化写法
    public int maxArea4(int[] height) {
        int l = 0, r = height.length - 1, water = 0;

        while (l < r) {
            int minH = Math.min(height[l], height[r]);
            water = Math.max(water, minH * (r - l));
            // 跳过不必要的计算比较
            while (l < r && height[l] <= minH) l++;
            while (l < r && height[r] <= minH) r--;
        }

        return water;
    }
}