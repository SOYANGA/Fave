package StrToInt;

/**
 * @program: TSRTOffer
 * @Description:  67. 把字符串转换成整数
 * @Author: SOYANGA
 * @Create: 2019-08-13 00:52
 * @Version 1.0
 */
public class Solution {
    public int StrToInt(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        boolean isNegative = str.charAt(0) == '-';
        int ret = 0;
        for (int i = 0; i <str.length(); i++) {
            char c = str.charAt(i);
            if(i == 0 && (c == '+' || c == '-')){ //符号判定上面已经判定过直接跳过
                continue;
            }
            if(c < '0' || c > '9') { //判断合法性
                return 0;
            }
            ret = ret * 10 + (c - '0'); //对数值进行处理
        }
        return isNegative ? -ret : ret;
    }
}
