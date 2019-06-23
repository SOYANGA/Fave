package Power_of_Two;

/**
 * @program: LeetCode
 * @Description: 231. Power of Two
 * @Author: SOYANGA
 * @Create: 2019-06-24 14:29
 * @Version 1.0
 */
public class PowerofTwo {
    public boolean isPowerOfTwo(int n) {
        return (n > 0) && (n & (n - 1)) == 0;
    }
}
/*
1.一个数是2的n次方 ，该数的比特位中则一定是且仅有一个1

则判断是否是2的幂则转变位判断x中1的个数

x!=0 x&(x-1)  = >count++  final count = 1;

2.mod

3.logx = int
 */
