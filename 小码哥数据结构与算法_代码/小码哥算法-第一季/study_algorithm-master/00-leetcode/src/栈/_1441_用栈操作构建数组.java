package 栈;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/build-an-array-with-stack-operations/
 * @author Rin
 *
 */
public class _1441_用栈操作构建数组 {
    public List<String> buildArray(int[] target, int n) {
    	int countList = 1;
    	int index = 0;
    	String push = "Push";
    	String pop = "Pop";
    	List<String> list = new ArrayList<>();
    	while(true) {
    		if (index >= target.length) return list;
    		if (countList == target[index]) {
    			list.add(push);
    			index++;
    		} else {
    			list.add(push);
    			list.add(pop);
    		}
    		countList++;
    	}
    }
}
