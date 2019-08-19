import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            String[] chars = str.split(",");
            if (chars == null || chars.length == 0) {
                System.out.println();
            }
            boolean[] flags = new boolean[chars.length];
            int[] nums = new int[chars.length];
            boolean flag = false;
            for (int i = 0; i < chars.length; i++) {
                try {
                    nums[i] = Integer.valueOf(chars[i]);
                } catch (NumberFormatException e) {
                    System.out.println("输入格式错误请重新输入");
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                for (int i = 0; i < nums.length; i++) {
                    if (flags[i]) {
                        continue;
                    }
                    flags[i] = true;
                    int n1 = nums[i];
                    int n2 = 100 - n1;
                    for (int j = i + 1; j < nums.length; j++) {
                        if (nums[j] == n2 && !flags[j]) {
                            flags[j] = true;
                            System.out.println(nums[i] + "," + nums[j]);
                            break;
                        }
                    }
                }
            }
        }
    }
}