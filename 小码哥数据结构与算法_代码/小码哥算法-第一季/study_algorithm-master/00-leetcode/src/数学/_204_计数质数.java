package 数学;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/count-primes/
 * @author linkage
 *
 */
public class _204_计数质数 {
	
	// 时间复杂度为O(n*n的根号)，会超时
    public int countPrimes(int n) {
    	if (n <= 2) return 0;
    	int count = 0;
    	// 0,1都不是质数
    	for(int i = 2; i <= n - 1; i++) {
    		if (isPrimes(i)) {
    			count++;
    		}
    	}
    	return count;
    }
    
    // 判断一个数是不是质数
    public boolean isPrimes(int n) {
    	
    	// j <= Math.sqrt(n)，开方影响性能
		for (int j = 2; j * j <= n; j++) {
			if (n % j == 0) {
				return false;
			}
		}
    	return true;
    }
    
	// 埃氏筛
    // https://leetcode-cn.com/problems/count-primes/solution/kuai-lai-miao-dong-shai-zhi-shu-by-sweetiee/
    public int countPrimes2(int n) {
    	boolean prim[] = new boolean[n];
    	Arrays.fill(prim, true);
    	int count = 0;
    	
    	// i <= Math.sqrt(n)，开方影响性能
    	for(int i = 2; i * i < n; i++) {
    		if (prim[i]) {
    			for(int j = i * i; j < n; j+=i) {
    				prim[j] = false;
    			}
    		}
    	}
    	
    	for(int i = 2; i < n; i++) {
    		if (prim[i]) {
    			count++;
    		}
    	}
    	return count;
    }
}
