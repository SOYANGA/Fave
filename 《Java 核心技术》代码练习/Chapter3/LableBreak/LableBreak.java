package Chapter3.LableBreak;

import java.util.Scanner;

public class LableBreak {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = 0;
        read_data1:
        while (n <= 0) {
            System.out.println(n);
            read_data2:
            for (int i = 0; i < 3; i++) {
                System.out.print("Enter a number >=0:");
                n = in.nextInt();
                if (n < 0) {
//                    break read_data1;   //直接跳出最外层被标记的循环。
                    continue read_data2; //跳出本次循环，进入被标记的循环进行判断
                } else {
                    System.out.println("************************");
                }
            }
        }
        System.out.println("jump Succeed!!");
    }
}


//break/continue 带标签  === goto(c语言)
//且标签只能放在嵌套或本次使用break/continue循环语句块的首部 冒号结尾 eg：lable:
//break 带标签意思是跳出被标记的循环
//contiue 带标签意思是跳出本次循环，进入被标记的循环进行判断

//由上：调试得出的