package Day_16;

import java.util.Scanner;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-21 10:37
 * @Version 1.0
 */
public class Test1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = in.nextInt();
        while ((count--) > 0) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            int number = in.nextInt();
            int kTimes = in.nextInt();
            int[] firstHalfArray = new int[number];
            int[] lastHalfArray = new int[number];
            for (int i = 0; i < firstHalfArray.length; i++) {
                firstHalfArray[i] = in.nextInt();
            }
            for (int i = 0; i < lastHalfArray.length; i++) {
                lastHalfArray[i] = in.nextInt();
            }
            shuffle(firstHalfArray, lastHalfArray, kTimes);
        }
    }

    private static void shuffle(int[] firstHalfArray, int[] lastHalfArray, int kTimes) {
        int result[] = new int[firstHalfArray.length * 2];
        while ((kTimes--) > 0) {
            for (int i = 0, j = 0, k = 0; k < result.length && i < firstHalfArray.length || j < lastHalfArray.length; k++) {
                if (i == j) {
                    result[k] = firstHalfArray[i];
                    i++;
                } else if (i > j) {
                    result[k] = lastHalfArray[j];
                    j++;
                }
            }
            for (int i = 0; i < result.length; i++) {
                if (i < firstHalfArray.length) {
                    firstHalfArray[i] = result[i];
                } else {
                    lastHalfArray[i - lastHalfArray.length] = result[i];
                }
            }
        }
        for (int i = 0; i < result.length; i++) {
            if (i == result.length - 1) {
                System.out.print(result[i] + "\n");
            } else {
                System.out.print(result[i] + " ");
            }
        }
    }
}
