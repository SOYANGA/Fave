import java.util.Scanner;


public class Work4_4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入你要查找完数得范围：");
        int range = scanner.nextInt();
        find(range);

        System.out.println("优化版：");
        //优化版 所有完数都是以6或8结尾
        findOP(range);
    }


    public static void find(int range) {
        for (int n = 0; n < range; n++) {
            int count = 0;
            for (int i = 1; i < n; i++) {
                if (n % i == 0) {
                    count += i;
                }
            }
            if (count == n && count != 0) {
                System.out.println(n);
            }
        }
    }


    //优化版 所有完数都是以6或8结尾
    public static void findOP(int range) {
        for (int n = 6; n < range; n++) {
            if (n % 10 == 6 || n % 10 == 8) {
                int count = 0;
                for (int i = 1; i < n; i++) {
                    if (n % i == 0) {
                        count += i;
                    }
                }
                if (count == n && count != 0) {
                    System.out.println(n);
                }
            }
        }
    }
}
