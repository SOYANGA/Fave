package FindNumsAppearOnce;

/**
 * @program: TSRTOffer
 * @Description: 56. 数组中只出现一次的数字 位运算
 * @Author: SOYANGA
 * @Create: 2019-08-02 01:05
 * @Version 1.0
 */
//num1,num2分别为长度为1的数组。传出参数
//将num1[0],num2[0]设置为返回结果
public class Solution {
    public void findNumsAppearOnce(int[] nums, int num1[], int num2[]) {
        int diff = 0;
        for (int num : nums) {
            diff ^= num; //异或相异为1，则获取都是两个数不同之处（位）
        }
        diff &= (-diff); //获取最低为的1的位；
        //（由于diff是两个数的异或，则一定相异为1，最低为1的位则成为区分两个数的标准将两个数区分开来）
        for (int num : nums) {
            if ((num & diff) == 0) {
                num1[0] ^= num;
            } else {
                num2[0] ^= num;
            }
        }
    }
}
