package Three_Sum;

import java.util.*;

/**
 * @program: LeetCode
 * @Description: 15.三数之和
 * @Author: SOYANGA
 * @Create: 2019-05-28 22:54
 * @Version 1.0
 */
public class ThreeSum {

    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            //排序
            Arrays.sort(nums);
            int n = nums.length;
            List<List<Integer>> res = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                //第一个数大于零则已经结束因为有序数组，不可能再三数之和等于零
                if (nums[i] > 0) {
                    break;
                }
                //去重
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                int left = i + 1;
                int right = n - 1;
                while (left < right) {
                    int temp = nums[i] + nums[left] + nums[right];
                    if (temp == 0) {
                        res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        //去重
                        while (left < right && nums[left] == nums[left + 1]) {
                            left += 1;
                        }
                        //去重
                        while (left < right && nums[right] == nums[right - 1]) {
                            right -= 1;
                        }
                        //两端同时缩进
                        left += 1;
                        right -= 1;
                    } else if (temp > 0) {
                        right -= 1;
                    } else {
                        left += 1;
                    }
                }
            }
            return res;
        }
    }

    class Solution2 {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> res = new LinkedList<>();
            if (nums == null || nums.length <= 2) {
                return res;
            }
            Arrays.sort(nums);
            for (int i = 0; i < nums.length; i++) {
                //第一个数大于零则已经结束因为有序数组，不可能再三数之和等于零
                if (nums[i] > 0) {
                    break;
                }
                //去重
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                int target = 0 - nums[i];
                int left = i + 1;
                int right = nums.length - 1;
                while (left < right) {
                    if (nums[left] + nums[right] == target) {
                        res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        //去重
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        //去重
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        //两端同时缩进
                        left++;
                        right--;
                    } else if (nums[left] + nums[right] < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
            return res;
        }
    }
}


