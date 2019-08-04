package Sum_Solution;

/**
 * @program: TSRTOffer
 * @Description: 64. 求 1+2+3+...+n
 * @Author: SOYANGA
 * @Create: 2019-08-13 00:22
 * @Version 1.0
 */
public class Solution {
    public int Sum_Solution(int n) {
        int sum = n;
        boolean flag = (n > 0) && ((sum += Sum_Solution(n - 1)) > 0);
        return sum;
    }
}
/*
本题无法使用if等循环语句，所以我们需要将执行递归不执行递归的终止条件找出来
从后往前递归，递归的终止条件就是n < 0,利用短路&&的特性，执行完第一个判断假如是true则继续执行下一个判断语句
反之第一个判断语句是false则不会执行，即可用其做递归的终止条件。
*/
