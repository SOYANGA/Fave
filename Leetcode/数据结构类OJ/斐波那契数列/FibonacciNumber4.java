package FibonacciNumber;

/**
 * @program: LeetCode
 * @Description: 509. Fibonacci Number dp空间优化法
 * @Author: SOYANGA
 * @Create: 2019-07-03 22:19
 * @Version 1.0
 */
public class FibonacciNumber4 {
    class Solution {
        public int fib(int N) {
            if (N <= 1) {
                return N;
            }
            int first = 0;
            int second = 1;
            for (int i = 0; i < N - 1; i++) {
                int sum = first + second;
                first = second;
                second = sum;
            }
            return second;
        }
    }
}
/*
O(n) 时间复杂度
O(1) 空间复杂度
 */
