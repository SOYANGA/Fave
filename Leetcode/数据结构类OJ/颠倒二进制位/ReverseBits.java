package Reverse_Bits;

/**
 * @program: LeetCode
 * @Description: 190. 颠倒二进制位
 * @Author: SOYANGA
 * @Create: 2019-06-24 14:27
 * @Version 1.0
 */
public class ReverseBits {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            int temp = n >> i;
            //1.取出要反转的每一位二进制
            temp &= 1;
            //2.将该二进制位放到反转后的位置
            temp <<= (31 - i);
            //3.将该位添加到结果上
            result |= temp;
        }
        return result;
    }
}
/**
 * 颠倒给定的 32 位无符号整数的二进制位。
 * 1.取出要反转的每一位二进制
 * 2.将该二进制位放到反转后的位置
 * 3.将该位添加到结果上
 */
