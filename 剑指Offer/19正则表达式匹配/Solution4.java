package match;

/**
 * @program: TSRTOffer
 * @Description: 19正则表达式 动态规划
 * @Author: SOYANGA
 * @Create: 2019-07-13 15:12
 * @Version 1.0
 */
public class Solution4 {
    public static boolean match(char[] str, char[] pattern) {
        if (str == null || pattern == null) {
            return false;
        }
        boolean[][] dp = new boolean[str.length + 1][pattern.length + 1];
        dp[str.length][pattern.length] = true;
        for (int i = str.length; i >= 0; i--) { //外需循环从 空字符串开始循环
            for (int j = pattern.length - 1; j >= 0; j--) { //内存循环 从 最后一个字符开始匹配
                //1.判断当前str 跟 pattern 的字符是否匹配
                boolean first_isMatch = (i != str.length) && (str[i] == pattern[j] || pattern[j] == '.');
                //2.pattern的下一个字符是'*'
                if (j < pattern.length - 1 && pattern[j + 1] == '*') {
                    dp[i][j] = dp[i][j + 2] || (first_isMatch && dp[i + 1][j]);
                } else {
                    //pattern 下一个字符不是‘*’
                    dp[i][j] = first_isMatch && dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }

}
/*


 * ============ *版本2.===============

 * 先看当前是否相等，再看下一个是否为“*”
 * 1.当前相等 立一个flag：
 * first_isMatch=i != str.length && (str[i] == pattern[j] || pattern[j] == '.')
 * 2.下一个是“*”
 *   无论当前是否相等，都可以跳过dp[i][j]=dp[i][j+2] ||
 *   或者，当前相等，那么 dp[i][j] = (first_isMatch && dp[i+1][j])
 *   综合得： dp[i][j] = dp[i][j+2] ||(first_isMatch && dp[i+1][j]);
 * 3.return dp[0][0]遍历完成
 */