package getMost;

/**
 * @program: TSRTOffer
 * @Description: 47 礼物最大价值 dp
 * @Author: SOYANGA
 * @Create: 2019-07-29 00:02
 * @Version 1.0
 */
public class Solution {
    public int getMost(int[][] board) {
        // write code here
        if (board == null || board.length == 0 || board[0].length == 0) {
            return 0;
        }
        int n = board[0].length;
        int[] dp = new int[n];
        for (int[] value : board) {
            dp[0] += value[0];
            for (int i = 1; i < n; i++) {
                dp[i] = Math.max(dp[i], dp[i - 1]) + value[i];
            }
        }
        return dp[n - 1];
    }
}
/*
利用foreach 以及覆盖记住前一行状态，再与当前行进行比较，最后得到最大值。
 */