package Day_42;

import java.util.Scanner;

/**
 * @program: EveryDayTest
 * @Description: 字符串加解密
 * @Author: SOYANGA
 * @Create: 2019-04-23 10:14
 * @Version 1.0
 */
public class Test2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String aucpassword = scanner.nextLine();
            String result = scanner.nextLine();
            encrypt(aucpassword);
            unEncrypt(result);
        }
    }


    public static void encrypt(String aucpassword) {
        char[] str = aucpassword.toCharArray();
        for (int i = 0; i < str.length; i++) {
            if (Character.isDigit(str[i])) {
                if (str[i] == '9') {
                    str[i] = '0';
                } else {
                    str[i] += 1;
                }
            }
            if (str[i] >= 'A' && str[i] <= 'z') {
                if (Character.isLowerCase(str[i])) {
                    if (str[i] == 'z') {
                        str[i] = 'A';
                    } else {
                        str[i] -= 31;
                    }
                } else {
                    if (str[i] == 'Z') {
                        str[i] = 'a';
                    } else {
                        str[i] += 33;
                    }
                }
            }
        }
        System.out.println(String.valueOf(str));
    }


    public static void unEncrypt(String result) {
        char[] str = result.toCharArray();
        for (int i = 0; i < str.length; i++) {
            if (Character.isDigit(str[i])) {
                if (str[i] == '0') {
                    str[i] = '9';
                } else {
                    str[i] -= 1;
                }
            }
            if (str[i] >= 'A' && str[i] <= 'z') {
                if (Character.isUpperCase(str[i])) {
                    if (str[i] == 'A') {
                        str[i] = 'z';
                    } else {
                        str[i] += 31;
                    }
                } else {
                    if (str[i] == 'a') {
                        str[i] = 'Z';
                    } else {
                        str[i] -= 33;
                    }
                }
            }
        }
        System.out.println(String.valueOf(str));
    }
}
