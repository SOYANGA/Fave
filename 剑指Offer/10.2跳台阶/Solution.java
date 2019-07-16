package JumpFloor;

/**
 * @program: TSRTOffer
 * @Description: 10.3跳台阶
 * @Author: SOYANGA
 * @Create: 2019-07-17 22:40
 * @Version 1.0
 */
public class Solution {
    public int JumpFloor(int n) {
        if(n <= 2){
            return n;
        }
        int pre2 = 1;
        int pre1 = 2;
        int res = 1;
        for(int i = 3; i <= n; i++){
            res = pre2 + pre1;
            pre2 = pre1;
            pre1 = res;
        }
        return res;
    }
}

/*
递推公式 状态方程 空间复杂度的优化
*/