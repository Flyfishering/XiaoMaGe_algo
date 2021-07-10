package 栈;

import java.util.HashMap;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/valid-parentheses/
 * @author Rin
 *
 */
public class _20_有效的括号 {
	
	// 利用栈后进先出，先进后出的特性解答
    public boolean isValid(String s) {
    	Stack<Character> stack = new Stack<>();
    	int len = s.length();
    	for(int i = 0; i < len; i++) {
    		char c = s.charAt(i);
    		if(c == '(' || c == '[' || c == '{') {
    			stack.push(c);
    		} else {
    			if(stack.isEmpty()) {
    				return false;
    			}
    			char left = stack.pop();
    			if(left == '(' && c != ')') return false;
    			if(left == '[' && c != ']') return false;
    			if(left == '{' && c != '}') return false;
    		}
    	}
    	return stack.isEmpty();
    }
    
	// 在1的基础上，使用hashmap
    public boolean isValid2(String s) {
    	Stack<Character> stack = new Stack<>();
    	HashMap<Character, Character> map = new HashMap<>();
    	map.put('{', '}');
    	map.put('(', ')');
    	map.put('[', ']');
    	int len = s.length();
    	for(int i = 0; i < len; i++) {
    		char c = s.charAt(i);
    		if(map.containsKey(c)) {
    			stack.push(c);
    		} else {
    			if(stack.isEmpty()) {
    				return false;
    			}
    			if (c != map.get(stack.pop())) return false;
    		}
    	}
    	return stack.isEmpty();
    }

	// {[()]}
	// 若成对，则替换结束后为空串
	// 但是时间复杂度和空间复杂度都非常不好。。。涉及字符串的查找，以及会不断创建新字符串
    public boolean isValid3(String s) {
    	while(s.contains("{}")
    			|| s.contains("()")
    			|| s.contains("[]")) {
    		s.replace("{}", "");
    		s.replace("()", "");
    		s.replace("{}", "");
    	}
    	return s.isEmpty();
    }
}
