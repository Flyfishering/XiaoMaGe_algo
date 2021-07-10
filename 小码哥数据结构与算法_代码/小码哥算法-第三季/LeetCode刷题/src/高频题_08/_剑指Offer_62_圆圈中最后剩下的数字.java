package 高频题_08;

/*
* https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/
* */

/*
* 记住解题工时 f(n,m) = (f(n-1),m) + m )%n
* */
public class _剑指Offer_62_圆圈中最后剩下的数字 {
    /*
    * 解法1:递归实现
    * */
    public int lastRemaining(int n, int m) {
        if (n == 1) return 0;
        return (lastRemaining(n-1,m) + m ) % n;
    }

    /*
    * 解法2: 迭代实现
    * */
    // f(1, 3) = 0
    // f(2, 3) = (f(1, 3) + 3) % 2
    // ...
    // f(7, 3) = (f(6, 3) + 3) % 7
    // f(8, 3) = (f(7, 3) + 3) % 8
    // f(9, 3) = (f(8, 3) + 3) % 9
    // f(10, 3) = (f(9, 3) + 3) % 10
    public int lastRemaining1(int n, int m) {
       int res= 0;
       for (int i = 2; i <=n ; i++) {
            res = (res + m ) % i;  //i是数据规模，代表有多少个数字（有多少个人）
       }
       return res;
    }
}
