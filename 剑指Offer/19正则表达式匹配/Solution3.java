package match;

/**
 * @program: TSRTOffer
 * @Description: 19正则表达式 动态规划
 * @Author: SOYANGA
 * @Create: 2019-07-13 14:15
 * @Version 1.0
 */
public class Solution3 {
    public static void main(String[] args) {
        char[] str = new char[]{'a', 'a', 'a'};
        char[] pattern = new char[]{'a', 'c', '*', 'a', 'b', '*', 'a'};
        System.out.println(match(str, pattern));
    }


    public static boolean match(char[] str, char[] pattern) {
        if (str == null || pattern == null) {
            return false;
        }
        boolean[][] dp = new boolean[str.length + 1][pattern.length + 1]; //状态表示某个匹配点是否返回匹配
        dp[str.length][pattern.length] = true;
        //开始循环 从空串开始匹配
        for (int i = str.length; i >= 0; i--) {
            //内存循环从最后一个字符进行匹配
            for (int j = pattern.length - 1; j >= 0; j--) {
                //后一个字母是'*'
                if (j < pattern.length - 1 && pattern[j + 1] == '*') {
                    //当前字符匹配
                    if (i < str.length && (str[i] == pattern[j] || pattern[j] == '.')) {
                        dp[i][j] = dp[i][j + 2] || dp[i + 1][j];
                    } else {
                        //当前字符不匹配
                        dp[i][j] = dp[i][j + 2];
                    }
                } else {
                    //后一个字母不是pattern ，看当前是否相等
                    if (i != str.length && (str[i] == pattern[j] || pattern[j] == '.')) {
                        dp[i][j] = dp[i + 1][j + 1];
                    }
                }
            }
        }
        return dp[0][0];
    }
}



  /* 有了前面的认识，我们考虑用动态规划解题，动态规划有正向的和反向的，到底怎么取呢？
     * 看下前面的递归调用：match1(str, i + 1, pattern, j + 1)相当于 dp[i][j]=dp[i+1][j+1]
     * 适合反向遍历，于是，我们可以初始化boolean dp[len1+1][len2+1] 其中len1=str.length,len2=pattern.length
     * 初始化dp[len1][len2]=true,含义是：str=aaa 和pattern=aa* 从末尾开始匹配 "" 和 "" 一定为true
     * 这个时候开始循环
     * 1.外循环：因为我们要用aa*匹配aaa,以aaa为外循环,注意，从""开始匹配接下来a,aa,aaa
     * for(int i = len1;i>=0;i--)
     * 2.内循环：拿aa*取匹配：匹配顺序 "*" "a*" "aa*",于是
     * for(int j = len2 - 1;j>=0;j--)
     * 循环体内部逻辑，参考递归调用：
     * =============  *版本1：=============

     * 先看下一个是否是“*”，再看当前是否相等
     * 1.若下一个是"*",分为当前相等和当前不等
     *      1.1：当前相等dp[i][j]=dp[i][j+2] || dp[i+1][j]
     *      1.2：当前不等dp[i][j]=dp[i][j+2]
     * 2.若不是"*",但是当前相等 d[i][j]= dp[i + 1][j + 1];
 */