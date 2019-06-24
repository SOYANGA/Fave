package Climbing_Stairs;

/**
 * @program: LeetCode
 * @Description: 70. 爬楼梯 递归+记忆化
 * @Author: SOYANGA
 * @Create: 2019-06-25 00:44
 * @Version 1.0
 */
public class ClimbingStairs1 {
    public int climbStairs(int n) {
        int memo[] = new int[n + 1];
        return climb_Stairs(0, n, memo);
    }

    public int climb_Stairs(int i, int n, int memo[]) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        if (memo[i] > 0) {
            return memo[i];
        }
        memo[i] = climb_Stairs(i + 1, n, memo) + climb_Stairs(i + 2, n, memo);
        return memo[i];
    }
}
/*
其中 memo为记忆数组 i为走过的台阶数  n为要走的台阶数

时间复杂度时O(n)

空间复杂度O(n)
 */