package FibonacciNumber;

/**
 * @program: LeetCode
 * @Description: 509. Fibonacci Number 递归+记忆化+剪枝
 * @Author: SOYANGA
 * @Create: 2019-07-03 22:19
 * @Version 1.0
 */
public class FibonacciNumber2 {

    public static void main(String[] args) {
        System.out.println(fib(3));
    }

    public static int fib(int N) {
        int[] memo = new int[N + 1];
        return fibn(N, memo);
    }

    public static int fibn(int n, int[] memo) {
        if (n <= 1) {
            memo[n] = n;
            return n;
        }

        if (memo[n - 1] == 0) {
            memo[n - 1] = fibn(n - 1, memo);
        }

        if (memo[n - 2] == 0) {
            memo[n - 2] = fibn(n - 2, memo);
        }

        memo[n] = memo[n - 1] + memo[n - 2];
        return memo[n];
    }
}

/*
O(2^n)时间复杂度
O(N) 空间复杂度
 */
