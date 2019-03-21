package Day_14;

import java.util.Scanner;

/*
组个最小数
 */
/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-20 00:20
 * @Version 1.0
 */
public class Test1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums = new int[10];
        int count = 0;
        StringBuilder str = new StringBuilder();
        while (count < 10 && scanner.hasNextInt()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            nums[count++] = scanner.nextInt();
        }
        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i];
            while ((temp--) != 0) {
                str.append(i);
            }
        }
        while ((nums[0]--) > 0) {
            str.insert(1, 0);
        }
        System.out.println(str.toString());
    }
}
