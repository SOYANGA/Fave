package Fibonacci;

/**
 * @program: TSRTOffer
 * @Description: 10.1斐波那契额数列 O(1)解法
 * @Author: SOYANGA
 * @Create: 2019-07-14 01:51
 * @Version 1.0
 */
public class Solution3 {
    private int[] fib = new int[40];

    public Solution3() {
        fib[1] = 1;
        for(int i = 2; i < fib.length; i++){
            fib[i] = fib[i-1] + fib[i-2];
        }
    }

    public int Fibonacci(int n) {
        return fib[n];
    }
}
