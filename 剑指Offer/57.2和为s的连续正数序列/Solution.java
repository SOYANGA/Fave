package FindContinuousSequence;

/**
 * @program: TSRTOffer
 * @Description: 57.2 和为 S 的连续正数序列 双指针法后指针先走，直到大于，前指针再走
 * @Author: SOYANGA
 * @Create: 2019-08-02 01:14
 * @Version 1.0
 */

import java.util.ArrayList;

public class Solution {
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        int start = 1; //开始
        int end = 2; //结束
        int curSum = 3; //当前和
        while (end < sum) {
            if (curSum > sum) { //前指针+1
                curSum -= start;
                start++;
            } else if (curSum < sum) { //后指针+1
                end++;
                curSum += end;
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = start; i <= end; i++) {
                    list.add(i);
                }
                //前后指针都往后走一下
                ret.add(list);
                curSum -= start;
                start++;
                end++;
                curSum += end;
            }
        }
        return ret;
    }
}
