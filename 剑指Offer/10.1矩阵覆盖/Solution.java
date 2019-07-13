package rectCover;

/**
 * @program: TSRTOffer
 * @Description: 10.2矩形覆盖 数学递推找规律
 * @Author: SOYANGA
 * @Create: 2019-07-14 01:56
 * @Version 1.0
 */
public class Solution {
    public int RectCover(int target) {
        if (target <= 2) {
            return target;
        }
        int pre2 = 1;
        int pre1 = 2;
        int res = 0;
        for (int i = 3; i <= target; i++) {
            res = pre2 + pre1;
            pre2 = pre1;
            pre1 = res;
        }
        return res;
    }
}

/*
数学归纳法：求出通项公式
*/
