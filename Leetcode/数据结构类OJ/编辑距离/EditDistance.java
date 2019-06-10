package Edit_Distance;

/**
 * @program: LeetCode
 * @Description: 72. 编辑距离 动态规划-经典题
 * @Author: SOYANGA
 * @Create: 2019-06-27 01:04
 * @Version 1.0
 */
public class EditDistance {

    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }
        int n = word1.length();
        int m = word2.length();
        int dp[][] = new int[n + 1][m + 1]; //空出0 0位置
        //初始化当word2为空时，word1需要不断删除操作 即dp[i-1][j]操作 且顺便初始化dp[0][0] = 0;
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
            //dp[i][0] = dp[i-1][0]+1;
        }
        //初始化当word1位空时，word1需要不断插入操作，即dp[0][j-1]操作
        for (int j = 1; j <= m; j++) {
            dp[0][j] = j;
            //dp[0][j] = dp[0][j-1]+1;
        }

        //因为递推公式:
        //word[i] = word[j]:word的第i位和word的第j位相等则dp[i][j] = dp[i-1]dp[j-1];
        //word1[i] != word2[j]，dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1
        //由于已经初始化好了0的位置则i,j 均是从1开始 则表示word[i]则位word[i-1],同理word[j-1]
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }
        return dp[n][m];
    }
}

/*
动态规划
状态：dp[i][j] 代表 word1 到 i 位置转换成 word2 到 j 位置需要最少步数

又因为当word[i] = word[j]:word的第i位和word的第j位相等则dp[i][j] = dp[i-1]dp[j-1];

当 word1[i] != word2[j]，dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1

其中，dp[i-1][j-1] 表示替换操作，dp[i-1][j] 表示删除操作，dp[i][j-1] 表示插入操作。

注意，针对第一行，第一列要单独考虑，我们引入 '' 下图所示：
https://pic.leetcode-cn.com/76574ab7ff2877d63b80a2d4f8496fab3c441065552edc562f62d5809e75e97e-Snipaste_2019-05-29_15-28-02.png

第一行，是 word1 为空变成 word2 最少步数，就是插入操作

第一列，是 word2 为空，需要的最少步数，就是删除操作
 */
