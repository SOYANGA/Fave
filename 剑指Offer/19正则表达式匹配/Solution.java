package match;

/**
 * @program: TSRTOffer
 * @Description: 19正则表达式 递归
 * @Author: SOYANGA
 * @Create: 2019-07-13 14:15
 * @Version 1.0
 */
public class Solution {


    public boolean match(char[] str, char[] pattern) {
        if (str == null || pattern == null) {
            return false;
        }
        return match(str, 0, pattern, 0);
    }

    private boolean match(char[] str, int i, char[] pattern, int j) {
        if (j == pattern.length) {
            //此时pattern遍历完了，如果str也遍历完了返回true， 反之false
            return str.length == i;
        }
        //parrten 的下一个字符是'*‘
        if (j < pattern.length - 1 && pattern[j + 1] == '*') {
            //当前字符匹配 两种情况 跳过str 或者跳过 pattern
            if (str.length != i && (str[i] == pattern[j] || pattern[j] == '.')) {
                return match(str, i, pattern, j + 2) || match(str, i + 1, pattern, j);
            } else { //当前不匹配
                return match(str, i, pattern, j + 2);
            }
        } else { //pattern的下一个字符不是'*'
            //当前字符匹配
            if (str.length != i && (str[i] == pattern[j] || pattern[j] == '.')) {
                return match(str, i + 1, pattern, j + 1);
            } else { //当前字符不匹配 直接返回false
                return false;
            }
        }
    }
}
/* 讨论2种：先看 * 再看 匹配
 * 前提：当pattern遍历完，return取决于str是否遍历完，str恰好遍历完才返回true，再接下来讨论
 *  1.若当前字符存在下一个字符，看下一个字符是否是 '*'，如果是，有2种情况
 *      一：当前匹配
 *      1.1match(str,i + 1,pattern,j)//跳过str
 *      1.2match(str,i,pattern,j + 2)//跳过pattern
 *      二：当前不匹配
 *      match(str,i,pattern,j + 2)//跳过pattern
 * 2.下一个不是 *
 *     当前匹配 return match(str,i + 1,pattern,j + 1)
 *     不匹配 直接返回false
 */