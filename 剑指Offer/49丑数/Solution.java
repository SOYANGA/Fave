package GetUglyNumber_Solution;

/**
 * @program: TSRTOffer
 * @Description: 49 丑数
 * @Author: SOYANGA
 * @Create: 2019-07-29 00:22
 * @Version 1.0
 */
public class Solution {
    public int GetUglyNumber_Solution(int N) {
        if (N <= 6) {
            return N;
        }
        int i2 = 0, i3 = 0, i5 = 0;
        int dp[] = new int[N];
        dp[0] = 1;
        for (int i = 1; i < N; i++) {
            int next2 = dp[i2] * 2;
            int next3 = dp[i3] * 3;
            int next5 = dp[i5] * 5;
            dp[i] = Math.min(next2, Math.min(next3, next5));
            if (dp[i] == next2) {
                i2++;
            }
            if (dp[i] == next3) {
                i3++;
            }
            if (dp[i] == next5) {
                i5++;
            }
        }
        return dp[N - 1];
    }
}
/*
特殊的dp方式 dp方程组即为丑数排序数组（Ｎ之内的），求出2 3 5 的丑数 最小值并更新对用的下标，依次递进
*/
