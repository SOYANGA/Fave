package Day_27;

import java.util.Scanner;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-04-02 23:09
 * @Version 1.0
 */
public class Test1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int count = in.nextInt();
            int[] nums = new int[count];
            for (int i = 0; i < count; i++) {
                nums[count] = in.nextInt();
            }
            int key = in.nextInt();
            boolean flag = false;
            for (int i = 0; i < nums.length; i++) {
                if (key == nums[i]) {
                    flag = true;
                    System.out.println(i);
                    break;
                }
            }
            if (!flag) {
                System.out.println(-1);
            }
        }
    }
}
