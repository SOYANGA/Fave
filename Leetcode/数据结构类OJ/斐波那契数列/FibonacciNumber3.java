package FibonacciNumber;

/**
 * @program: LeetCode
 * @Description: 509. Fibonacci Number dp法
 * @Author: SOYANGA
 * @Create: 2019-07-03 22:19
 * @Version 1.0
 */
public class FibonacciNumber3 {
    class Solution {
        public int fib(int N) {
            if(N <= 1){
                return N;
            }
            int[] dp = new int[N+1];
            dp[0] = 0;
            dp[1] = 1;
            for(int i = 2; i <= N ; i++){
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            return dp[N];
        }
    }
}
/*
O(n) 时间复杂度
O(n) 空间复杂度
 */