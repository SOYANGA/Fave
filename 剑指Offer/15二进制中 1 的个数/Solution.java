package numberOf1;

/**
 * @program: TSRTOffer
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-07-12 17:09
 * @Version 1.0
 */
public class Solution {

    public int NumberOf1(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n &= (n - 1);
        }
        return count;
    }

}
