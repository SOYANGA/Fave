package Day_44;

import java.util.Scanner;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-04-24 22:41
 * @Version 1.0
 */
public class Test2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String str = in.nextLine();
            String[] strings = str.split("[^a-zA-Z]");
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = strings.length - 1; i >= 0; i--) {
                String string = strings[i].trim();
                if (!"".equals(string)) {
                    stringBuilder.append(string).append(" ");
                }
            }
            System.out.println(stringBuilder.substring(0, stringBuilder.length() - 1));
        }
    }
}
