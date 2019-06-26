package Coin_Change;

/**
 * @program: LeetCode
 * @Description: 322. 零钱兑换 动态规划
 * @Author: SOYANGA
 * @Create: 2019-06-26 23:51
 * @Version 1.0
 */
public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            //设置标志 排除小于给定零钱最小值的情况
            dp[i] = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (coin <= i && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}
/*
动态规划
        状态dp[] :为每一个价格对应的最少硬币数

        dp[i] = min(dp[i-coins[j]])+1 coins为可选的硬币数组j为数组下标即 coins[j] = 所有硬币单位

        coins[0] = 1 coins[1] = 2 coins[2] = 11

        dp[i] = min(dp[i - coins[j]]) +1 = min(dp[i - coins[0]] , i - coins[1]], i - coins[2]])+1

        且需要给所有的i提前标记，避免使用比coins数组中最小的面值还要小的数

        最后进行判断，是否可以凑成这个金额

        时间复杂度：O(amount * coins.length) O(n^2)
*/