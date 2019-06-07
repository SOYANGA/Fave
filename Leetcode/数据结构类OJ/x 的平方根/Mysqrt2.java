package Mysqrt;

/**
 * @program: LeetCode
 * @Description: 69. x 的平方根 牛顿迭代法
 * @Author: SOYANGA
 * @Create: 2019-06-09 02:18
 * @Version 1.0
 */
public class Mysqrt2 {
    class Solution {
        public int mySqrt(int x) {
            if (x == 0 || x == 1) {
                return x;
            }
            long result = x;
            while (result > x / result) {
                result = (result + x / result) / 2;
            }
            return (int) result;
        }
    }
}
/*
    2. 牛顿迭代法  x(n+1）=x(n）－f(x(n))/f'(x(n)）
        x的平方根
        则 y = x^2带入 f(x) = y = x^2 - y0
        x(n+1）=x(n）- (xn^2-y0)/2xn =(xn+y0/xn)/2
 */