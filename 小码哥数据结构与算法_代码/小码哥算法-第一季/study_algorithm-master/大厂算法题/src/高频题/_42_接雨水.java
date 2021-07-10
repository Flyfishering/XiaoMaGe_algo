package 高频题;

import java.util.Stack;

public class _42_接雨水 {
    // 求每根柱子能装多少水。每根柱子能装的水，
    // 取决于该柱子左边最高的柱子与右边最高的柱子的最小值，以及该柱子自身的高度
    // 终极优化方法里面认为，没必要求出左右两边的最小值，因为要用的，只是
    // 左右两边的最小值，以及该柱子的高度
    public int trap(int[] height) {
        if (height == null || height.length == 0) return 0;

        int lastIdx = height.length - 2;
        int water = 0;
        int[] leftMaxes = new int[height.length];
        for (int i = 1; i <= lastIdx; i++) {
            leftMaxes[i] = Math.max(height[i - 1], leftMaxes[i - 1]);
        }

        int[] rightMaxes = new int[height.length];
        for (int i = lastIdx; i > 0; i--) {
            rightMaxes[i] = Math.max(height[i + 1], rightMaxes[i + 1]);
        }

        // 遍历每一根柱子，看看每一根柱子上能放多少水(头尾的柱子都不能放水)
        for (int i = 1; i <= lastIdx; i++) {
            // 求出左边最大右边最大中的较小者
            int min = Math.min(leftMaxes[i], rightMaxes[i]);
            // 说明这根柱子能放水
            if (min > height[i]) {
                water += min - height[i];
            }
        }

        return water;
    }

    // 优化。leftMaxes数组不需要存在
    public int trap2(int[] height) {
        if (height == null || height.length == 0) return 0;

        int lastIdx = height.length - 2;
        int water = 0;

        int[] rightMaxes = new int[height.length];
        for (int i = lastIdx; i > 0; i--) {
            rightMaxes[i] = Math.max(height[i + 1], rightMaxes[i + 1]);
        }

        int leftMax = 0;
        // 遍历每一根柱子，看看每一根柱子上能放多少水(头尾的柱子都不能放水)
        for (int i = 1; i <= lastIdx; i++) {
            leftMax = Math.max(leftMax, height[i - 1]);
            // 求出左边最大右边最大中的较小者
            int min = Math.min(leftMax, rightMaxes[i]);
            // 说明这根柱子能放水
            if (min > height[i]) {
                water += min - height[i];
            }
        }

        return water;
    }

    // 空间复杂度O(1),时间复杂度O(n)的优化
    // 当前柱子能接到的雨水，只与左右最高柱子中较低的那根柱子的高度有关系。根本不需要知道高的柱子是谁，高度是多少！
    public int trap3(int[] height) {
        if (height == null || height.length == 0) return 0;

        int water = 0, lowerMax = 0, li = 0, ri = height.length - 1;

        while (li < ri) {
            // lower:左边索引柱子跟右边索引柱子里面最小的那一根
            int lower = height[li] <= height[ri] ? height[li++] : height[ri--];
            // 到目前为止遇到的最大的lower(求过的所有lower里面最大的那一个。)
            lowerMax = Math.max(lowerMax, lower);
            // 当前格子的水，就是lowerMax(lower中最大的，肯定存在>=lowerMax的柱子) - lower(当前格子，是li跟ri中高度小的那个格子)
            water += lowerMax - lower;
        }

        return water;
    }

    // 比较容易想的一种优化，不用数组，分别用一个变量来记录左边，右边的最大值
    public int trap4(int[] height) {
        if (height == null || height.length == 0) return 0;

        int li = 0, ri = height.length - 1, water = 0;
        int leftMax = 0, rightMax = 0;

        while (li < ri) {
            if (height[li] <= height[ri]) {
                if (height[li] < leftMax) {
                    water += leftMax - height[li];
                } else {
                    leftMax = height[li];
                }
                li++;
            } else {
                if (height[ri] < rightMax) {
                    water += rightMax - height[ri];
                } else {
                    rightMax = height[ri];
                }
                ri--;
            }
        }
        return water;
    }

    // 单调栈
    // https://leetcode-cn.com/problems/trapping-rain-water/solution/trapping-rain-water-by-ikaruga/
    public int trap5(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int water = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                // cur:当前要计算水量的柱子
                // i:cur右边的柱子(高度大于cur)
                // left:cur左边的柱子(高度大于等于cur)
                int cur = stack.pop();
                // 若没有左边柱子，则水量为0，跳出
                if (stack.isEmpty()) break;
                int left = stack.peek();
                int h = Math.min(height[left], height[i]) - height[cur];
                water += h * (i - left - 1);
            }
            stack.push(i);
        }
        return water;
    }
}
