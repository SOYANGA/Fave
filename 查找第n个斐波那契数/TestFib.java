import java.util.Scanner;

public class TestFib {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你要找第几个斐波那契数：");
        int n = scanner.nextInt();
        System.out.println("结果为：" + fib2(n));
        System.out.println("结果为：" + fib1(n));
        scanner.close();
    }

    public static int fib1(int n) { // 递归版
        if (n > 46) {
            System.out.println("传入的数字太大，只能查找前46个斐波那契数");
            return 0;
        }
        if (n <= 2) {
            return 1;
        } else {
            return fib1(n - 1) + fib1(n - 2);
        }
    }

    public static int fib2(int n) { // 非递归
        if (n > 46) {
            System.out.println("传入的数字太大，只能查找前46个斐波那契数");
            return 0;
        }
        int rut;
        int pre_rut;
        int next_rut;
        rut = pre_rut = 1;
        while (n > 2) {
            next_rut = pre_rut;
            pre_rut = rut;
            rut = pre_rut + next_rut;
            n--;
        }
        return rut;
    }
}