package Day_48;


/**
 * @program: EveryDayTest
 * @Description: 左右最值最大差
 * @Author: SOYANGA
 * @Create: 2019-04-29 17:58
 * @Version 1.0
 */
public class Test1 {
    public static void main(String[] args) {
        int[] num = new int[]{2, 7, 3, 1, 1};
        System.out.println(findMaxGap(num, 5));
    }

    public static int findMaxGap(int[] num, int n) {
        int index = n - 2;
        int result = Integer.MIN_VALUE;
        for (int i = 0; i <= index; i++) {
            int rightMax = Integer.MIN_VALUE;
            int leftMax = Integer.MIN_VALUE;
            for (int ri = 0; ri <= i; ri++) {
                if (rightMax < num[ri]) {
                    rightMax = num[ri];
                }
            }
            for (int le = i + 1; le < num.length; le++) {
                if (leftMax < num[le]) {
                    leftMax = num[le];
                }
            }
            int tempResult = Math.abs(rightMax - leftMax);
            if (tempResult > result) {
                result = tempResult;
            }
        }
        return result;
    }
}
