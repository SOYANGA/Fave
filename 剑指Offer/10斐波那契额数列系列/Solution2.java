package Fibonacci;

/**
 * @program: TSRTOffer
 * @Description: 10.1斐波那契额数列 dp解法
 * @Author: SOYANGA
 * @Create: 2019-07-14 01:51
 * @Version 1.0
 */
public class Solution2 {
    public int Fibonacci(int n) {
        if (n < 1) {
            return n;
        }
        int[] fib = new int[n + 1];
        fib[1] = 1;
        for (int i = 2; i <= n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib[n];
    }
}