package Day_42;

import java.util.Scanner;

/**
 * @program: EveryDayTest
 * @Description: 计算一年中的第几天
 * @Author: SOYANGA
 * @Create: 2019-04-23 10:13
 * @Version 1.0
 */
public class Test1 {
    private final static int[] days = new int[]{0, 31, -1, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int year = scanner.nextInt();
            int month = scanner.nextInt();
            int day = scanner.nextInt();

            if (iConverDateToDay(year, month, day)) {
                System.out.println(getOutDay(month, day));
            } else {
                System.out.println("-1");
            }
        }
    }


    public static boolean iConverDateToDay(int year, int month, int day) {
        //TODO 判断月份是否合法
        if (isLeapYear(year)) {
            days[2] = 29;
        } else {
            days[2] = 28;
        }
        if (year > 0 && month <= 12 && month > 0 && day <= days[month] && day > 0) {
            return true;
        } else {
            return false;
        }

    }

    public static int getOutDay(int moth, int day) {
        //TODO 输出合法月份是这一年的第几天
        int num = 0;
        for (int i = 1; i < moth; i++) {
            num += days[i];
        }
        num += day;
        return num;
    }

    public static boolean isLeapYear(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            return true;
        } else {
            return false;
        }
    }

}
