package power;

/**
 * @program: TSRTOffer
 * @Description:16 整值得整数次方
 * @Author: SOYANGA
 * @Create: 2019-07-17 23:59
 * @Version 1.0
 */
public class Solution2 {
    public double Power(double base, int exponent) {
        long exp = exponent;
        if (exp < 0) {
            base = 1 / base;
            exp = -exp;
        }
        double x = base;
        double pow = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                pow *= x;
            }
            x *= x;
            exp >>= 1;
        }
        return pow;
    }
}
/*
 x^n = (x*x)^(n/2) n为偶数
 x^n = x*(x*x)^(n/2) n为奇数
*/

