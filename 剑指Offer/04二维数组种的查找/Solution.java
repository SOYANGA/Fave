package Find;

/**
 * @program: TSRTOffer
 * @Description: 4 二维数组中的查找 找规律问题
 * @Author: SOYANGA
 * @Create: 2019-07-14 01:10
 * @Version 1.0
 */
public class Solution {
    public boolean Find(int target, int[][] array) {
        if (array == null || array.length == 0 || array[0].length == 0) {
            return false;
        }
        //确定行列
        int rows = array.length;
        int cols = array[0].length;
        //根据题意要找是否存在该数，则应该从右上角开始查找，
        //因为该位置所有比他小的数都偶在该位置的左边，所有比该数大的都在该数的下面
        int r = 0;
        int c = cols - 1;
        while (r < rows && c >= 0) {
            if (array[r][c] == target) {
                return true;
            }
            if (array[r][c] > target) {
                c--;
            } else {
                r++;
            }
        }
        return false;
    }
}