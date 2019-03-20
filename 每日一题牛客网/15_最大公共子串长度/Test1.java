package Day_15;

import java.util.Scanner;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-20 23:16
 * @Version 1.0
 */
public class Test1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String str1 = in.nextLine();
            String str2 = in.nextLine();
            String maxStr = str1.length() > str2.length() ? str1 : str2;
            String minStr = str1.length() > str2.length() ? str2 : str1;
            int strLength = minStr.length();
            int maxLehgth = 0;
            String maxCommonStr = "";
            for (int i = 0; i < strLength; i++) {
                for (int j = i + 1; j <=strLength; j++) {
                    if (maxStr.contains(minStr.substring(i, j)) && j - i > maxLehgth) {
                        maxLehgth = j - i;
                        maxCommonStr = minStr.substring(i, j);
                    }
                }
            }
            System.out.println(maxCommonStr.length());
        }
    }
}
