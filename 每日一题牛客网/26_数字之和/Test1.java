package Day_26;

import java.util.Scanner;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-04-01 23:08
 * @Version 1.0
 */
public class Test1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int num = in.nextInt();
            int numprw2 = num * num;
            int numSum = sumEveryNum(num);
            int numprw2Sum = sumEveryNum(numprw2);
            System.out.println(numSum + " " + numprw2Sum);
        }
    }

    private static int sumEveryNum(int num) {
        if (num / 10 == 0) {
            return num;
        }
        int sum = 0;
        int unit = 0;
        while (((unit = num % 10) != 0) || (num / 10 != 0)) {
            sum += unit;
            num /= 10;
        }
        return sum;
    }
}
