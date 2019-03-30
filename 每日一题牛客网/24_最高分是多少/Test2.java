package Day_24;

import sun.security.util.AuthResources_it;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.Scanner;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-30 22:43
 * @Version 1.0
 */
public class Test2 {

    public static void main(String[] args) {
        int opNum = 0;
        int studentNum = 0;
        int i;
        int startIndex = 0;
        int endIndex = 0;
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            studentNum = in.nextInt();
            opNum = in.nextInt();
            int[] score = new int[studentNum];
            for (i = 0; in.hasNext() && i < studentNum; i++) {
                score[i] = in.nextInt();
            }

            String op = null;

            for (i = 0; in.hasNext() && i < opNum; i++) {
                op = in.next();
                startIndex = in.nextInt();
                endIndex = in.nextInt();
                process(op, startIndex, endIndex, score);
            }
        }

    }

    private static void process(String op, int a, int b, int[] score) {
        int begin, end;
        if ("Q".equals(op)) {
            end = Math.max(a, b);
            begin = Math.min(a, b) - 1;
            int max = score[begin];
            for (int i = begin; i < end; i++) {
                if (max < score[i]) {
                    max = score[i];
                }
            }
            System.out.println(max);
        } else if ("U".equals(op)) {
            score[a - 1] = b;
        }
    }
}

//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNextInt()) {
//            int studentNum = in.nextInt();
//            int opNum = in.nextInt();
//            int[] studentID = new int[studentNum];
//            for (int i = 0; i < studentNum; i++) {
//                studentID[i] = in.nextInt();
//            }
//            while ((opNum--) > 0) {
//                char op = in.next().charAt(0);
//                if ('Q' == op) {
//                    int startIndex = in.nextInt() - 1;
//                    int endIndex = in.nextInt() - 1;
//                    startIndex = Math.min(startIndex, endIndex);
//                    endIndex = Math.max(startIndex, endIndex);
//                    endIndex = startIndex > endIndex ? startIndex : endIndex;
//                    int max = 0;
//                    for (int i = startIndex; i <= endIndex; i++) {
//                        if (max < studentID[i]) {
//                            max = studentID[i];
//                        }
//                    }
//                    System.out.println(max);
//                } else if ('U' == op) {
//                    int index = in.nextInt() - 1;
//                    int updateNum = in.nextInt();
//                    studentID[index] = updateNum;
//                }
//            }
//        }
//    }
//}
