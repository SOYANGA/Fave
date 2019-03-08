package Day_05;

import java.util.Scanner;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-08 16:42
 * @Version 1.0
 */
public class Test2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n = in.nextInt();
            if (n < 1) {
                System.out.println(-1);
            } else {
                long count = n * 2 + (n * (n - 1) * 3) / 2;
                System.out.println(count);
            }
        }
        in.close();
    }
}

//通项公式:
//        An=A1+(n-1)d
//        An=Am+(n-m)d
//        等差数列的前n项和:
//        Sn=[n(A1+An)]/2
//        Sn=nA1+[n(n-1)d]/2