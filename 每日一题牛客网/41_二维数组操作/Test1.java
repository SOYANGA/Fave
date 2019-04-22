package Day_41;

import java.util.Scanner;

/**
 * @program: EveryDayTest
 * @Description: 二维数组操作
 * @Author: SOYANGA
 * @Create: 2019-04-21 23:14
 * @Version 1.0
 */
public class Test1 {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int row = scanner.nextInt();
            int column = scanner.nextInt();
            int x1 = scanner.nextInt();
            int y1 = scanner.nextInt();

            int x2 = scanner.nextInt();
            int y2 = scanner.nextInt();

            int insertRow = scanner.nextInt();
            int insertColumn = scanner.nextInt();

            int x3 = scanner.nextInt();
            int y3 = scanner.nextInt();

            if (row <= 9 && column <= 9) {
                System.out.println(0);
            } else {
                System.out.println(-1);
                System.out.println(-1);
                System.out.println(-1);
                System.out.println(-1);
                System.out.println(-1);
                continue;
            }
            if ((x1 >= 0 && x1 < row) && (x2 >= 0 && x2 < row) && (y1 >= 0 && y1 < column) && (y2 >= 0 && y2 < column)) {
                System.out.println(0);
            } else {
                System.out.println(-1);
            }

            if (insertRow >= 0 && insertRow < row) {
                System.out.println(0);
            } else {
                System.out.println(-1);
            }

            if (insertColumn >= 0 && insertColumn < column) {
                System.out.println(0);
            } else {
                System.out.println(-1);
            }

            if ((x3 >= 0 && x3 < row) && (y3 >= 0 && y3 < column)) {
                System.out.println(0);
            } else {
                System.out.println(-1);
            }
        }
        scanner.close();
    }
}

