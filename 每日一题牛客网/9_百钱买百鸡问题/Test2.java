package Day_09;

import java.util.ArrayList;
import java.util.Scanner;


/*
公元前五世纪，我国古代数学家张丘建在《算经》一书中提出了“百鸡问题”：鸡翁一值钱五，鸡母一值钱三，鸡雏三值钱一。百钱买百鸡，问鸡翁、鸡母、鸡雏各几何？

详细描述：

接口说明

原型：

int GetResult(vector &list)

输入参数：

        无

输出参数（指针指向的内存区域保证有效）：

    list  鸡翁、鸡母、鸡雏组合的列表

返回值：

     -1 失败

     0 成功

     x + y + z = 100
     5x + 3y + z/3 = 100

     x =4 * z / 3 - 100;
     y =200 - 7 * z / 3;

     且 x>=0&& y>=0&&z%3==0
        75<=z<=600/7
        75<=z<=85


 */

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-12 19:13
 * @Version 1.0
 */
public class Test2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int num = scanner.nextInt();
            for (int i = 75; i <= 85; i++) {
                int checkNumber = 4 * i / 3 - 100;
                int henNumber = 200 - 7 * i / 3;
                if (i % 3 == 0) {
                    System.out.println(checkNumber + " " + henNumber + " " + i);
                }
            }
        }
        scanner.close();
    }
}

//    Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNextInt()) {
//                for (int i = 75; i <= 85; i++) {
//                int checkNumber = 4 * i / 3 - 100;
//                int henNumber = 200 - 7 * i / 3;
//                if (i % 3 == 0) {
//                System.out.println(checkNumber + " " + henNumber + " " + i);
//                }
//                }
//                }
//                scanner.close();
//                }

//    Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNextInt()) {
//        int num = scanner.nextInt();
//        System.out.println(
//                "0 25 75\n" +
//                        "4 18 78\n" +
//                        "8 11 81\n" +
//                        "12 4 84");
//    }
//        scanner.close();
//}