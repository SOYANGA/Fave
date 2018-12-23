package FindEntrynumber;

class Solution {
    public int pivotIndex(int[] nums) {
        if (nums.length < 2) {
            return -1;
        }
        int sum = 0; //数组中每个元素之和
        int leftSum = 0;//中心数组以左数组元素之和
        for (int temp : nums) {  //求和
            sum += temp;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {     //由于中心数组不计算在总数组元素之和之内，所以下标这样计算
                leftSum = 0;
            } else {  //左数组数据求和
                leftSum += (nums[i - 1]);
            }
            if (leftSum == (sum - leftSum - nums[i])) {  //当满足左边数组元素之和等于总数组数据之和得一半时，返回当前数组下标
                return i;
            }
        }
        return -1;  //反之返回-1
    }
}