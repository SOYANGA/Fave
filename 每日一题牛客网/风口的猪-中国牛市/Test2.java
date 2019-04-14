package Day_35;

import java.util.Scanner;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-04-13 22:43
 * @Version 1.0
 */
public class Test2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            int count = in.nextInt();
            int[] num = new int[count];
            for (int i = 0; i < count; i++) {
                num[i] = in.nextInt();
            }
            System.out.println(calculateMax(num));
        }
    }

    public static int calculateMax(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int maxSum = 0;
        for (int i = 1; i < prices.length; i++) {
            int temp = maxValue(prices, 0, i) + maxValue(prices, i + 1, prices.length - 1);
            if (temp > maxSum) {
                maxSum = temp;
            }
        }
        return maxSum;
    }

    public static int maxValue(int[] prices, int left, int right) {
        if (left >= prices.length) {
            return 0;
        }
        int Min = prices[left];
        int ret = 0;
        for (int i = left + 1; i <= right; i++) {
            Min = Math.min(prices[i], Min); //最佳买入点
            ret = Math.max(ret, prices[i] - Min); //最大收益
        }
        return ret;
    }
}

