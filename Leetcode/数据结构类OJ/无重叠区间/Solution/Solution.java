package Non_overlapping_Intervals.Solution;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @program: LeetCode
 * @Description: 435. 无重叠区间 贪心
 * @Author: SOYANGA
 * @Create: 2019-08-19 22:03
 * @Version 1.0
 */
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        //按照结尾进行排序 顺序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        //cnt标识没有重叠的区间
        int cnt = 1;
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end) {
                continue;
            }
            //区间不重叠更新尾端的值
            end = intervals[i][1];
            cnt++;
        }
        //返回要删去的重叠的区间
        return intervals.length - cnt;
    }

}
