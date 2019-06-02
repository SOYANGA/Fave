package PowXN;

/**
 * @program: LeetCode
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-06-01 01:23
 * @Version 1.0
 */
public class PowXN {

    public static void main(String[] args) {
//        System.out.println(Math.abs(Integer.MIN_VALUE));   Math.abs(Integer.MIN_VALUE) = Integer.MIN_VALUE; n的边界值
        System.out.println(myPow(1, -2145565447));
    }

    public static double myPow(double x, int n) {
        double res = pow(x, Math.abs(n));
        return n >= 0 ? res : 1 / res;
    }

    public static double pow(double x, int n) {
        double pow = 1;
        while (n != 0) {
            if (n % 2 != 0) {
                pow *= x;
            }
            x *= x;
            n /= 2;
        }
        return pow;
    }

}
