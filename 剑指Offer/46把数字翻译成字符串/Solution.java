package numDecodings;

/**
 * @program: TSRTOffer
 * @Description: 46 把数字翻译成字符串
 * @Author: SOYANGA
 * @Create: 2019-07-28 23:49
 * @Version 1.0
 */
class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i <= n; i++) {
            int one = Integer.valueOf(s.substring(i - 1, i));
            if (one != 0) {
                dp[i] += dp[i - 1];
            }
            if (s.charAt(i - 2) == '0') {
                continue;
            }
            int two = Integer.valueOf(s.substring(i - 2, i));
            //前面s.charAt(i-1) == '0'已经把0~9剔除掉了已经
            if (two <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }
}
/*
dp解法
dp[i] = dp[i-1] +dp[i-2]
但是有条件
当首位不为0时 dp = dp[i-1] 永远有
当首位在10 ~ 26 之间 时 dp = dp[i-2] 成立
如果两个条件都满足则有dp[i] = dp[i-1] +dp[i-2]

排列组合问题
*/
