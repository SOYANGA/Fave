package Fibonacci;

/**
 * @program: TSRTOffer
 * @Description: 10.1斐波那契额数列 动规空间优化解法
 * @Author: SOYANGA
 * @Create: 2019-07-14 01:51
 * @Version 1.0
 */
public class Solution {
    public int Fibonacci(int n) {
        if(n < 1) {
            return n;
        }
        int pre2 = 0;
        int pre1 = 1;
        int fib = 0;
        fib = 1;
        for(int i = 2; i <= n; i++) {
            fib = pre1 + pre2;
            pre2 = pre1;
            pre1 = fib;
        }
        return fib;
    }
}
