package 栈;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/
 * @author linkage
 *
 */
public class _剑指Offer31_栈的压入_弹出序列 {
	// 模拟入栈出栈
    public boolean validateStackSequences(int[] pushed, int[] popped) {
    	Stack<Integer> stack = new Stack<>();
    	int index = 0;
    	for(int num : pushed) {
    		stack.push(num);
    		while (!stack.isEmpty() && stack.peek() == popped[index]) { // 要先判断栈是否为空
    			stack.pop();
    			index++;
    		}
    	}
    	// 模拟入出栈，若最终栈中无元素，则说明弹出顺序是对的。
    	if (stack.isEmpty()) {
    		return true;
		} else { // 反之，说明弹出顺序是错误的
    		return false;
		}
    }
}
