package Day_20;

import java.util.Arrays;
import java.util.Collections;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-25 22:41
 * @Version 1.0
 */
public class Test1 {
    public static void main(String[] args) {
        int[] gifts = new int[]{1, 2, 3, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4};
        System.out.println(getValue(gifts, gifts.length));

    }

    public static int getValue(int[] gifts, int n) {
        Arrays.sort(gifts);
        int count = 0;
        int temp = gifts[n / 2];
        for (int i = 0; i < n; i++) {
            if (gifts[i] == temp) {
                count++;
            }
        }
        return count > (n / 2) ? temp : 0;
    }

    public class Gift {
        public int getValue(int[] gifts, int n) {
            int gift = -1, times = 0;
            for (int i = 0; i < n; i++) {
                if (times == 0) {
                    gift = gifts[i];
                }
                if (gift == gifts[i]) {
                    times++;
                } else {
                    times--;
                }
            }
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (gift == gifts[i]) {
                    count++;
                }
            }
            return count > n / 2 ? gift : 0;

        }
    }
}
