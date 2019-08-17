package ACKDXF;

/**
 * @program: TSRTOffer
 * @Description: 找出输入的数字且排序
 * @Author: SOYANGA
 * @Create: 2019-08-16 18:23
 * @Version 1.0
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            //存放数字的结果集
            ArrayList<Integer> list = list = new ArrayList<>();
            String str = in.nextLine();
            char[] chars = str.toCharArray();
            //字符串是否函数有数字标志位
            boolean flag = true;
            for (char aChar : chars) {
                if (aChar >= '0' && aChar <= '9') {
                    list.add(Integer.valueOf(aChar + ""));
                    flag = false;
                }
            }
            //包含数字的结果集中如果有数字执行以下逻辑，反之输出-1
            if (!flag) {
                Collections.sort(list);
                for (int n : list) {
                    System.out.print(n);
                }
                System.out.println();
            } else {
                System.out.println(-1);
            }
        }
    }
}