package LemonadeChange;

/**
 * @program: LeetCode
 * @Description: 860. 柠檬水找零
 * @Author: SOYANGA
 * @Create: 2019-06-02 12:19
 * @Version 1.0
 */
public class LemonadeChange {
    class Solution {
        public boolean lemonadeChange(int[] bills) {
            if (bills[0] != 5) {
                return false;
            }
            int five = 0;
            int ten = 0;
            for (int change : bills) {
                if (change == 5) { //5 不用找
                    five++;
                } else if (change == 10) {  //10 找5
                    if (five <= 0) {
                        return false;
                    }
                    five--;
                    ten++;
                } else {      //20 找15
                    if (five > 0 && ten > 0) { //10+5优先使用
                        five--;
                        ten--;
                    } else if (five >= 3) {  //5+5+5
                        five -= 3;
                    } else {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
