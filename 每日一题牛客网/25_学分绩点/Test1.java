package Day_25;

import java.util.Scanner;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-31 23:51
 * @Version 1.0
 */
public class Test1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            float sum = 0;
            int creditsum = 0;

            int count = in.nextInt();
            int[] credits = new int[count];
            int[] grades = new int[count];
            for (int i = 0; i < count; i++) {
                credits[i] = in.nextInt();
                creditsum += credits[i];
            }
            for (int i = 0; i < count; i++) {
                grades[i] = in.nextInt();
            }
            for (int i = 0; i < count; i++) {
                sum += (hasGPA(grades[i]) * credits[i]);
            }
            System.out.format("%.2f", sum / creditsum);

        }
    }

    private static float hasGPA(int grade) {
        float GPA = 0;
        if (grade >= 90 && grade <= 100) {
            GPA = 4.0f;
        } else if (grade >= 85 && grade <= 89) {
            GPA = 3.7f;
        }else if(grade>=82&&grade<=84){
            GPA = 3.3f;
        } else if (grade >= 78 && grade <= 81) {
            GPA = 3.0f;
        } else if (grade >= 75 && grade <= 77) {
            GPA = 2.7f;
        } else if (grade >= 72 && grade <= 74) {
            GPA = 2.3f;
        } else if (grade >= 68 && grade <= 71) {
            GPA = 2.0f;
        } else if (grade >= 64 && grade <= 67) {
            GPA = 1.5f;
        } else if (grade >= 60 && grade <= 63) {
            GPA = 1.0f;
        }
        return GPA;
    }
}
