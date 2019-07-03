package FibonacciNumber;

/**
 * @program: LeetCode
 * @Description: 509. Fibonacci Number 斐波那契递推公式法 fib(n) = ( Math.pow((1+sqrt5)/2,N)-Math.pow((1-sqrt5)/2,N) )/sqrt5
 * @Author: SOYANGA
 * @Create: 2019-07-03 22:19
 * @Version 1.0
 */
public class FibonacciNumber5 {
    class Solution {
        public int fib(int N) {
            double sqrt5 = Math.sqrt(5);
            double fibn = Math.pow((1 + sqrt5) / 2, N) - Math.pow((1 - sqrt5) / 2, N);
            return (int) (fibn / sqrt5);
        }
    }
}
/*
Olog(n) 时间复杂度
O(1) 空间复杂度
 */
