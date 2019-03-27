package Day_21;

import java.util.Scanner;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-26 23:34
 * @Version 1.0
 */
public class Test1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            System.out.println(getTotalCount(scanner.nextInt()));
        }
    }

    public static int getTotalCount(int monthCount) {
        if (monthCount < 3) {
            return 1;
        }
        int monthone = 1;
        int mounthtwo = 0;
        int mounththree = 0;
        for (int i = 2; i <= monthCount; i++) {
            mounththree += mounthtwo;
            mounthtwo = monthone;
            monthone = mounththree;

        }
        return monthone + mounthtwo + mounththree;
    }

}
