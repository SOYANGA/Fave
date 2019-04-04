package Day_29;

import java.util.Scanner;

/**
 * @program: EveryDayTest
 * @Description: 年会抽奖
 * @Author: SOYANGA
 * @Create: 2019-04-04 22:11
 * @Version 1.0
 */
/*
A(2,2) = 所有排列组成的个数
 */
public class Test1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            double num = in.nextDouble();
            //得出得规律
            if (num > 6) {
                System.out.println("36.79%");
                continue;
            }
            double denominator = num;
            //排列求解 分母  -阶乘
            for (double i = num - 1; i >= 1; i--) {
                denominator *= i;
            }
            //错排问题求解 分子
            // M(n) = (n-1)[M(n-1)+M(n-2)]
            double numerator = literalSort(num);
            double probability = numerator / denominator * 100;
            System.out.format("%.2f%%\n", probability);
        }
    }

    private static double literalSort(double num) {
        if (num == 1) {
            return 0;
        }
        if (num == 2) {
            return 1;
        } else {
            return (num - 1) * (literalSort(num - 1) + literalSort(num - 2));
        }
    }
}
