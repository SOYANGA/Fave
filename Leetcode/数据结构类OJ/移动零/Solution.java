package MoveZero;

class Solution {
    public void moveZeroes(int[] nums) {
        int j = 0;  //定位非零下标
        for(int i = 0;i<nums.length;i++){
            if(nums[i]!=0){
                nums[j++]=nums[i];
            }
        }
        for(int i = j;i<nums.length;i++){//给去除零的非零数组后增加0
            nums[i] = 0;
        }
    }
}