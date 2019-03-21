package Day_16;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-21 10:37
 * @Version 1.0
 */
public class Test2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            int num = in.nextInt();
            int array[] = new int[num];
            HashMap<Integer, Integer> map = new HashMap();
            for (int i = 0; i < array.length; i++) {
                int temp = in.nextInt();
                array[i] = temp;
                if (map.containsKey(temp)) {
                    map.put(temp, map.get(temp) + 1);
                } else {
                    map.put(temp, 1);
                }
            }
            int keyGrade = in.nextInt();
            System.out.println(map.get(keyGrade));
        }
    }
}
