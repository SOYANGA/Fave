package isContinuous;

import java.util.Arrays;

/**
 * @program: TSRTOffer
 * @Description: 61扑克牌顺子
 * @Author: SOYANGA
 * @Create: 2019-08-12 23:36
 * @Version 1.0
 */

public class Solution {
    public boolean isContinuous(int[] numbers) {
        if (numbers.length < 5) {
            return false;
        }
        int cnt = 0;
        //统计大小王的个数
        for (int num : numbers) {
            if (num == 0) {
                cnt++;
            }
        }
        Arrays.sort(numbers);
        //使用大小王去补全顺子 总牌数等于nums.length - cnt cnt为0跳过
        for (int i = cnt; i < numbers.length - 1; i++) {
            if (numbers[i + 1] == numbers[i]) {
                return false;
            }
            cnt -= (numbers[i + 1] - numbers[i] - 1); //相邻两个排之间的间隔
        }
        return cnt >= 0;
    }
}
/*
审清楚题意：简化题干，就是要凑顺子，按照来癞子数来凑顺子
*/
