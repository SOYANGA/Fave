package match;

/**
 * @program: TSRTOffer
 * @Description: 19 正则表达式
 * @Author: SOYANGA
 * @Create: 2019-07-13 14:15
 * @Version 1.0
 */
public class Solution2 {
    public boolean match(char[] str, char[] pattern) {
        if (str == null || pattern == null) {
            return false;
        }
        return match(str, 0, pattern, 0);
    }

    private boolean match(char[] str, int i, char[] pattern, int j) {
        if (j == pattern.length) {//pattern匹配完了
            return str.length == i;//如果str也匹配完了，返回true，反之返回false;
        }
        //1.先看str与pattern是否匹配
        boolean first_isMatch = (i != str.length) && (str[i] == pattern[j] || pattern[j] == '.');
        //2.再看pattern下一个字符是否是'*'
        if (j < pattern.length - 1 && pattern[j + 1] == '*') {
            //前者为是否当前字符匹配都有的情况，后者是当前字符匹配才有的情况
            return match(str, i, pattern, j + 2) || (first_isMatch && match(str, i + 1, pattern, j));
        } else { //pattern 的下一个字符不是'*'
            return first_isMatch && match(str, i + 1, pattern, j + 1);
        }
    }
}

/* 这里是第二个思路， 反过来，先看匹配 ，再看 *
 * 前提：当pattern遍历完，return取决于str是否遍历完，再接下来讨论
 * 1.先看当前字符是否匹配 记录first_isMatch
 * 2.再看下一个字符是否为 '*'
 *      2.1当前匹配first_isMatch && match(str,i + 1,pattern,j)
 *      2.2无论匹配与否match(str,i,pattern,j + 2)//跳过
 * 3.不匹配*，当前字符匹配的前提下，进入到下一个循环
 * else first_isMatch && match(str,i + 1,pattern,j + 1)
 */