package Climbing_Stairs;

/**
 * @program: LeetCode
 * @Description: 70. 爬楼梯 动态规划（反向递推） 找到最有子结构
 * @Author: SOYANGA
 * @Create: 2019-06-25 00:45
 * @Version 1.0
 */
public class ClimbingStairs2 {

    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
