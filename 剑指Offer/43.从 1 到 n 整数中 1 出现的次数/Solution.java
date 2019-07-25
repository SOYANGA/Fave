package numberOf1Between1AndN_Solution;

/**
 * @program: TSRTOffer
 * @Description: 43 从1到n整数中1出现的次数 数学规律题
 * @Author: SOYANGA
 * @Create: 2019-07-27 00:32
 * @Version 1.0
 */
public class Solution {
    public int NumberOf1Between1AndN_Solution(int n) {
        int count = 0;
        for (long m = 1; m <= n ; m *= 10) {
            count += (n / m + 8) / 10 * m + (n / m % 10 == 1 ? n % m + 1 : 0);
        }
        return count;
    }
}
/*
解析：https://www.cnblogs.com/xuanxufeng/p/6854105.html
https://blog.csdn.net/u013132035/article/details/80768636
*/
