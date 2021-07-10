package 队列;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/implement-stack-using-queues/
 * 用单个队列实现
 * @author Rin
 *
 */
public class _225_用队列实现栈 {
	
	private Queue<Integer> queue = new LinkedList<>();
    /** Initialize your data structure here. */
    public _225_用队列实现栈() {

    }
    
    /** Push element x onto stack. */
    public void push(int x) {
    	queue.offer(x);
    	for(int i = 0; i < queue.size() - 1; i++) {
    		queue.offer(queue.poll());
    	}
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
    	return queue.poll();
    }
    
    /** Get the top element. */
    public int top() {
    	return queue.peek();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
    	return queue.isEmpty();
    }
}

/**
 * 用双队列实现
 * @author Rin
 *
 */
class MyStack {
	private Queue<Integer> queue1 = new LinkedList<>(); 
	private Queue<Integer> queue2 = new LinkedList<>(); // 临时队列
    /** Initialize your data structure here. */
    public MyStack() {

    }
    
    /** Push element x onto stack. */
    public void push(int x) {
    	queue2.offer(x);
    	while(!queue1.isEmpty()) {
    		queue2.offer(queue1.poll());
    	}
    	queue1 = queue2;
    	queue2 = new LinkedList<>();
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
    	return queue1.poll();
    }
    
    /** Get the top element. */
    public int top() {
    	return queue1.peek();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
    	return queue1.isEmpty();
    }
}
