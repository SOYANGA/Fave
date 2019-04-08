package Day_32;

import java.lang.ref.PhantomReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-04-08 22:21
 * @Version 1.0
 */
public class Test1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            String[] moneys = in.nextLine().split(" ");
            String need = moneys[0];
            String give = moneys[1];
            String[] needs = need.split("\\.");
            String[] gives = give.split("\\.");
//            Map<String,Integer> needmap = new HashMap<>();
            boolean flag = true;
            if (Integer.parseInt(needs[0]) > Integer.parseInt(gives[0])) {
                String[] temp = needs;
                needs = gives;
                gives = temp;
                flag = false;
            }
            int[] residueMoney = new int[3];
            int[] systems = new int[]{17, 29};
            int count = 0;
            for (int i = 2; i > 0; i--) {
                int needMoney = Integer.parseInt(needs[i]);
                int giveMoney = Integer.parseInt(gives[i]);
                int residue = giveMoney - needMoney + count;
                if (residue >= 0) {
                    residueMoney[i] = residue;
                } else {
                    residueMoney[i] = residue + systems[i - 1];
                    count = -1;
                }
            }

            residueMoney[0] = Integer.parseInt(gives[0]) - Integer.parseInt(needs[0]) + count;
            if (flag) {
                System.out.print(residueMoney[0]);
            } else {
                System.out.print("-" + residueMoney[0]);
            }
            for (int i = 1; i < 3; i++) {
                System.out.print("." + residueMoney[i]);
            }
            System.out.println();
        }
    }
}
