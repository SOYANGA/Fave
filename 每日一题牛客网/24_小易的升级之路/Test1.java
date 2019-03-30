package Day_24;

import java.util.Scanner;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-30 22:43
 * @Version 1.0
 */
public class Test1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            int count = in.nextInt();
            int ablity = in.nextInt();
            int[] beat = new int[count];
            while ((count--) > 0) {
                beat[count] = in.nextInt();
            }
            for (int i = beat.length - 1; i >= 0; i--) {
                int beatnum = beat[i];
                if (beatnum <= ablity) {
                    ablity += beatnum;
                } else {
                    int addnum = ablity;
                    while ((ablity % addnum) != 0 || (beatnum % addnum != 0)) {
                        addnum--;
                    }
                    ablity += addnum;
                }
            }
            System.out.println(ablity);
        }
    }
}
