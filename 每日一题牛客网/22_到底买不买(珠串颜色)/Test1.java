package Day_22;

import java.util.Scanner;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-27 22:06
 * @Version 1.0
 */
public class Test1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String giveStr = in.nextLine();
            String needStr = in.nextLine();
            char[] gives = giveStr.toCharArray();
            char[] needs = needStr.toCharArray();

            int count = 0;
            for (int i = 0; i < needs.length; i++) {
                for (int j = 0; j < gives.length; j++) {
                    if (needs[i] == gives[j]) {
                        count++;
                        gives[j] = '#';
                        break;
                    }
                }
            }
            if (count == needs.length) {
                System.out.println("Yes" + " " + (gives.length - count));
            } else {
                System.out.println("No" + " " + (needs.length - count));
            }
        }
    }
}
