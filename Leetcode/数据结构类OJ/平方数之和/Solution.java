package SumOfSquareNumbers;

/**
 * @program: TSRTOffer
 * @Description:633 平方数之和
 * @Author: SOYANGA
 * @Create: 2019-08-19 17:02
 * @Version 1.0
 */
public class Solution {
    public boolean judgeSquareSum(int c) {
        long start = 0;
        long end = (long) Math.sqrt(c);
        while (start <= end) {
            long num = start * start + end * end;
            if (num < c) {
                start++;
            } else if (num > c) {
                end--;
            } else {
                return true;
            }
        }
        return false;
    }
}

