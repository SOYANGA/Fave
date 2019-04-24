package Day_44;

import java.util.Scanner;

/**
 * @program: EveryDayTest
 * @Description: 骆驼命名法
 * @Author: SOYANGA
 * @Create: 2019-04-24 22:41
 * @Version 1.0
 */
public class Test1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String name = in.nextLine();
            String[] names = name.split("_");
            StringBuilder transName = new StringBuilder(names[0]);
            for (int i = 1; i < names.length; i++) {
                transName.append(names[i].substring(0, 1).toUpperCase()).append(names[i].substring(1));
            }
            System.out.println(transName);
        }
    }
}

