package Add;

/**
 * @program: TSRTOffer
 * @Description: 65. 不用加减乘除做加法 利用位运算 并且考虑进位  ^ 和 &
 * @Author: SOYANGA
 * @Create: 2019-08-13 00:33
 * @Version 1.0
 */
public class Solution {
    public int Add(int num1, int num2) {
        while (num2 != 0) {
            int temp = num1 ^ num2;
            num2 = (num1 & num2) << 1;
            num1 = temp;
        }
        return num1;
    }

    //递归
    public int Add2(int num1, int num2) {
        return num2 == 0 ? num1 : Add(num1 ^ num2, (num1 & num2) << 1);
    }
}
/*
分为有进位，和无进位
无进位： a^b
有进位： (a & b) << 1

用num2作进位，num1做无尽为
*/
