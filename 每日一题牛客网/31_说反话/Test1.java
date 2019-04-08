package Day_31;

import java.util.Scanner;

/**
 * @program: EveryDayTest
 * @Description: 说反话
 * @Author: SOYANGA
 * @Create: 2019-04-08 20:31
 * @Version 1.0
 */
public class Test1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String string = in.nextLine();
            String[] words = string.split(" ");
            if (words.length < 2) {
                System.out.println(string);
            } else {
                System.out.print(words[words.length - 1]);
                for (int i = words.length - 2; i >= 0; i--) {
                    System.out.print(" " + words[i]);
                }
                System.out.println();
            }

        }
    }
}
