package isNumeric;

/**
 * @program: TSRTOffer
 * @Description: 20表示数值的字符串
 * @Author: SOYANGA
 * @Create: 2019-07-18 23:34
 * @Version 1.0
 */
public class Solution {
    public boolean isNumeric(char[] str) {
        if(str == null || str.length == 0) {
            return false;
        }
        return new String(str).matches("[+-]?\\d*(\\.\\d+)?([eE][+-]?\\d+)?");
    }
}
/*
使用正则表达式
[]  ： 字符集合
()  ： 分组
?   ： 重复 0 ~ 1 次
+   ： 重复 1 ~ n 次
*   ： 重复 0 ~ n 次
.   ： 任意字符
\\. ： 转义后的 .
\\d ： 数字

 */