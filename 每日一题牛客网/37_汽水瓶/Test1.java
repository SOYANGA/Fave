package Day_37;

import java.util.Scanner;

/**
 * @program: EveryDayTest
 * @Description: 汽水瓶
 * @Author: SOYANGA
 * @Create: 2019-04-18 18:25
 * @Version 1.0
 */
public class Test1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int drinknum = 0;
            int emptynum = in.nextInt();
            if (emptynum <= 0) {
                continue;
            }
            while (emptynum != 0) {
                if (emptynum >= 3) {
                    drinknum++;
                    emptynum -= 2;
                } else if (emptynum == 2) {
                    drinknum++;
                    emptynum = 0;
                } else {
                    emptynum = 0;
                }
            }
            System.out.println(drinknum);

        }
    }
}
