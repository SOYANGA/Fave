package MajorityElement;

/**
 * @program: LeetCode
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-02-10 22:23
 * @Version 1.0
 */
class Solution {
    public int majorityElement(int[] nums) {
        int count = 1;  //计数器
        int maj = nums[0];   //目标数据
        for (int i = 1; i < nums.length; i++) {
            if (maj == nums[i]) { //相同+1
            } else {
                count--; //计数器-1
                if (count == 0) { //如果计数器变为0（出现次数小于当前遍历过数组长度）
                    maj = nums[i + 1];//改变目标数据
                }
            }
        }
        return maj;
    }
}
