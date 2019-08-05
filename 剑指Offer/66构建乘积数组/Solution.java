package multiply;


/**
 * @program: TSRTOffer
 * @Description: 66. 构建乘积数组
 * @Author: SOYANGA
 * @Create: 2019-08-13 00:45
 * @Version 1.0
 */
public class Solution {
    public int[] multiply(int[] A) {
        int lengthA = A.length;
        int[] newNum = new int[lengthA];
        int ret = 1;
        for (int i = 0; i < lengthA; ret *= A[i++]) {
            newNum[i] = ret;
        }
        ret = 1;
        for (int i = lengthA - 1; i >= 0; ret *= A[i--]) {
            newNum[i] *= ret;
        }
        return newNum;
    }
}