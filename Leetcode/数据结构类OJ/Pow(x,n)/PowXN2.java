package PowXN;

import java.io.PrintWriter;

/**
 * @program: LeetCode
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-06-01 01:23
 * @Version 1.0
 */
public class PowXN2 {

    public static void main(String[] args) {
//        System.out.println(Math.abs(Integer.MIN_VALUE));   Math.abs(Integer.MIN_VALUE) = Integer.MIN_VALUE; n的边界值
        System.out.println(myPow(1, -2145565447));
        System.out.println(myPow2(1, -2145565447));
    }

    public static double myPow(double x, int n) {
        boolean isMinValue = false;
        if (n < 0) {
            x = 1 / x;
            if (n == Integer.MIN_VALUE) {
                n = -(n + 1);
                isMinValue = true;
            }
        }
        double newx = x;
        double pow = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                pow *= newx;
            }
            newx *= newx;
            n >>= 1;
        }
        if (isMinValue) {
            return pow * x;
        }
        return pow;
    }

    public static double myPow2(double x, int n) {
        long newn = n;
        if (newn < 0) {
            x = 1 / x;
            newn = -newn;
        }
        double newx = x;
        double pow = 1;
        while (newn > 0) {
            if ((newn & 1) == 1) {
                pow *= newx;
            }
            newx *= newx;
            newn >>= 1;
        }
        return pow;
    }

}
