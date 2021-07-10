package 栈队列_03;

import java.util.HashMap;
import java.util.Stack;

/*
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/valid-parentheses
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */
public class _20_有效的括号 {
    class Solution {
        // 1.使用栈的方式解决
        public boolean isValid(String s) {
            Stack<Character> stack = new Stack<>();
            int len = s.length();
            for (int i = 0; i < len; i++) {
                char c = s.charAt(i);
                if (c == '(' || c== '{' || c =='[') {
                    stack.push(c);
                } else {
                    if (stack.isEmpty()) return false;
                    char left = stack.pop();
                    if (left == '(' && c != ')') return false;
                    if (left == '{' && c != '}') return false;
                    if (left == '[' && c != ']') return false;
                }
            }
            return stack.isEmpty();
        }

        // 2.借用hashmap的方式解决
        public boolean isValid1(String s) {
            HashMap<Character,Character> map = new HashMap<>();
            Stack<Character> stack = new Stack<>();
            map.put('(',')');
            map.put('{','}');
            map.put('[',']');
            int len = s.length();
            for (int i = 0; i < len; i++) {
                char c = s.charAt(i);
                if (map.containsKey(c)) {
                    stack.push(c);
                } else {
                    if (stack.isEmpty()) return false;
                    char left = stack.pop();
                    if (c != map.get(left)) return false;
                }
            }
            return stack.isEmpty();
        }
    }
}
