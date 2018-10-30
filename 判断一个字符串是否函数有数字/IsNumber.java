import java.util.Scanner;

public class IsNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个要检查的字符串：");
        String str = scanner.nextLine();
        System.out.println("result:" + isNumber(str));
        scanner.close();
    }

    public static boolean isNumber(String str) {
        char[] s = str.toCharArray();
        for (int i = 0; i < s.length; i++) {
            if (s[i] > '0' && s[i] < '9') {
                return false;
            }
        }
        return true;
    }
}
