package 栈;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/score-of-parentheses/
 * @author Rin
 *
 */
public class _856_括号的分数_todo {
	
	// 栈(用到了递归思想，目前没理解)
	// TODO
    public int scoreOfParentheses1(String s) {
    	
    	Stack<Integer> stack = new Stack<>();
    	int res = 0;
    	for(char c : s.toCharArray()) {
    		if(c == '(') {
    			stack.push(res);
    			res = 0;
    		} else {
    			res = stack.pop() + Math.max(res << 1, 1);
    		}
    	}
    		
    	return res;
    }
	
	// 统计核心的数目
    public int scoreOfParentheses2(String s) {
    	// 一个紧挨着的()的左括号，不算深度
    	// 也可以深度初期定义成0，遇到右括号的时候，先减减再用！
    	int deep = -1;
    	int score = 0;
    	for(int i = 0; i < s.length(); i++) {
    		if(s.charAt(i) == '(') {
    			deep++;
    		} else {
    			if(s.charAt(i - 1) == '(') {
    				score += 1 << deep;
    			}
    			deep--;
    		}
    	}
    	return score;
    }
    
	// 栈
    // https://leetcode-cn.com/problems/score-of-parentheses/solution/ctu-jie-zhan-by-zao-shang-7dian-qi/
    // 需要继续加深理解 TODO
    public int scoreOfParentheses3(String s) {
    	
    	Stack<Integer> stack = new Stack<>();
    	int res = 0;
    	for(char c : s.toCharArray()) {
    		if(c == '(') {
    			stack.push(0);
    		} else {
    			if(stack.peek() == 0) {
    				stack.pop();
    				stack.push(1);
    			} else {
    				res = 0;
    				while(stack.peek() != 0) {
    					res += stack.pop();
    				}
    				res = res << 1;
    				stack.pop();
    				stack.push(res);
    			}
    		}
    	}
    	res = 0;
    	while(!stack.isEmpty()) {
    		res += stack.pop();
    	}
    	return res;
    }
}
