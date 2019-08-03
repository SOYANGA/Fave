package Minimum_Number_of_Arrows_to_Burst_Balloons;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @program: LeetCode
 * @Description: 452. 用最少数量的箭引爆气球 贪心
 * @Author: SOYANGA
 * @Create: 2019-08-19 22:05
 * @Version 1.0
 */
class Solution {
    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        //跟无重复区间不同的是，[1,2][2,3]是重复区间
        int cnt = 1, end = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] <= end) {
                continue;
            }
            cnt++;
            end = points[i][1];
        }
        //区间不重复的几个，就需要几只箭去删除
        return cnt;
    }
}
