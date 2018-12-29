package rotate;

class Solution {
    public void rotate(int[] nums, int k) {
        int size = nums.length;
        int[] temp = nums.clone();  //克隆一个一模一样的数组
        for(int i = 0;i < size;i++){
            int p = (i+k)%size;  //类似于循环数组，循环队列的做法
            nums[p]=temp[i];
        }
    }
}
