package JumpFloorII;

/**
 * @program: TSRTOffer
 * @Description: 10.4变态跳台阶
 * @Author: SOYANGA
 * @Create: 2019-07-17 22:41
 * @Version 1.0
 */
import java.lang.Math;

public class Solution {
    public int JumpFloorII(int n) {
        return (int)Math.pow(2, n-1);
    }
}

/**
 * 数学归纳法：
 * f(n) = f(n-1)+f(n-2)+f(n-3)+...+f(0);
 * f(n-1) = f(n-2)+f(n-3)+f(n-4)+...+f(0);
 * 两式相加的 f(n) = 2 * f(n-1); 等比数列
 * 所以 通项公式 a(n) = a1 * q^(n-1) a1 = 1 ;q = 2 ; an = 2 ^ (n-1);
 * s(n) = a1(1 - q^n)/(1 - q);
 * 求解即可
 */
