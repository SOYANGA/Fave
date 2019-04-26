package Day_45;

import java.util.Arrays;

/**
 * @program: EveryDayTest
 * @Description: 数组中出现次数超过一半的数字
 * @Author: SOYANGA
 * @Create: 2019-04-26 22:25
 * @Version 1.0
 */
public class Test1 {

    public static void main(String[] args) {
        int[] array = new int[]{2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5};
        System.out.println(MoreThanHalfNum_Solution(array));
    }

    public static int MoreThanHalfNum_Solution(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        int num = array[0];
        int times = 1;
        for (int i = 1; i < array.length; i++) {
            if (times == 0) {
                //UPDTE
                num = array[i];
                times = 1;
            } else if (array[i] == num) {
                times++;
            } else {
                times--;
            }
        }
        times = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == num) {
                times++;
            }
        }
        return times > array.length / 2 ? num : 0;

    }

}
