package 栈;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/implement-queue-using-stacks/
 * @author Rin
 *
 */
public class _232_用栈实现队列 {
	
	private Stack<Integer> inStack = new Stack<>();
	private Stack<Integer> outStack = new Stack<>();
	
    /** Initialize your data structure here. */
    public _232_用栈实现队列() {

    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
    	inStack.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
    	if(outStack.isEmpty()) {
    		while(!inStack.isEmpty()) {
    			outStack.push(inStack.pop());
    		}
    	}
    	return outStack.pop();
    }
    
    /** Get the front element. */
    public int peek() {
    	if(outStack.isEmpty()) {
    		while(!inStack.isEmpty()) {
    			outStack.push(inStack.pop());
    		}
    	}
    	return outStack.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
    	return (inStack.isEmpty() && outStack.isEmpty());
    }
}
