package Day_13;

import java.util.Scanner;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-17 23:00
 * @Version 1.0
 */
public class Test1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNextInt()) {//注意while处理多个case
            float list = in.nextInt();
            String cha = in.next();
            int row = Math.round((list / 2));
            StringBuilder str1 = new StringBuilder();
            StringBuilder str2 = new StringBuilder();
            float temp1 = list;
            float temp2 = list;
            while ((temp1--) > 0) {
                str1.append(cha);
            }
            str2.append(cha);
            while ((temp2--) > 2) {
                str2.append(" ");
            }
            str2.append(cha);
            for (int i = 0; i < row; i++) {
                if (i == 0 || i == row - 1) {
                    System.out.println(str1);
                } else {
                    System.out.println(str2);
                }
            }
        }
    }
}
//aaaaaaaaaa
//a        a
//a        a
//a        a
//aaaaaaaaaa