package printMatrix;

/**
 * @program: TSRTOffer
 * @Description: 29 顺时针打印矩阵 思路/调理清晰即可
 * @Author: SOYANGA
 * @Create: 2019-07-14 00:53
 * @Version 1.0
 */

import java.util.ArrayList;

public class Solution {
    public ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> ret = new ArrayList<>();
        //定义4个变量来控制方向
        int r1 = 0;
        int rows = matrix.length - 1;
        int c1 = 0;
        int clos = matrix[0].length - 1;
        while (r1 <= rows && c1 <= clos) {
            //左-->右
            for (int i = c1; i <= clos; i++) {
                ret.add(matrix[r1][i]);
            }
            //下-->上
            for (int i = r1 + 1; i <= rows; i++) {
                ret.add(matrix[i][clos]);
            }
            //为了防止最终出现一行的情况，会导致从左到右 和从下到上把这行已经打印结束了，不需要从右到左和从上到下了
            //右-->左
            if (r1 != rows) {
                for (int i = clos - 1; i >= c1; i--) {
                    ret.add(matrix[rows][i]);
                }
            }
            //下-->上
            if (c1 != clos) {
                for (int i = rows - 1; i > r1; i--) {
                    ret.add(matrix[i][c1]);
                }
            }
            //一圈转完进行调整
            r1++;
            rows--;
            c1++;
            clos--;
        }
        return ret;
    }
}
