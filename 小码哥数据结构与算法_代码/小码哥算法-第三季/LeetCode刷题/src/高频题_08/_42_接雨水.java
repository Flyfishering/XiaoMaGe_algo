package 高频题_08;

import java.util.Stack;

/*
*   https://leetcode-cn.com/problems/trapping-rain-water/
* */
public class _42_接雨水 {
    public int trap(int[] height) {
        int water = 0;
        if (height.length < 3) return 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {




            }
            stack.push(i);
        }
        return water;
    }
}
