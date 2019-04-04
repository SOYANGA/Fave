package Day_29;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @program: EveryDayTest
 * @Description:数字和为sum的方法数
 * @Author: SOYANGA
 * @Create: 2019-04-04 22:11
 * @Version 1.0
 */

/*
    动态规划 -01背包问题
 */
public class Test2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int sum = in.nextInt();
        int[] value = new int[n];
        long[] dp = new long[sum + 1];
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            value[i] = in.nextInt();
            for (int j = sum; j >= 0; j--) {
                if (j >= value[i]) {
                    dp[j] += dp[j - value[i]];
                }
            }
        }
        System.out.println(dp[sum]);
    }
}
