package 数组;

public class _面试题_17_21_直方图的水量 {
    public int trap(int[] height) {
        if (height == null || height.length < 2) return 0;

        int li = 0, ri = height.length - 1;
        int leftMax = 0, rightMax = 0, water = 0;

        while (li < ri) {
            if (height[li] <= height[ri]) {
                if (leftMax > height[li]) {
                    water += leftMax - height[li];
                } else {
                    leftMax = height[li];
                }
                li++;
            } else {
                if (rightMax > height[ri]) {
                    water += rightMax - height[ri];
                } else {
                    rightMax = height[ri];
                }
                ri--;
            }
        }

        return water;
    }
}
