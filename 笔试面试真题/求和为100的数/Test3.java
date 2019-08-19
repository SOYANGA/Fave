import java.util.Arrays;
import java.util.Scanner;

public class Test3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            String[] chars = str.split(",");
            if (chars == null || chars.length == 0) {
                System.out.println();
            }
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
            Arrays.sort(nums);
            if (!flag) {
                int i = 0;
                int j = nums.length - 1;
                while (i < j) {
                    int sum = nums[i] + nums[j];
                    if (sum == 100) {
                        System.out.println(nums[i++] + "," + nums[j--]);
                    } else if (sum < 100) {
                        i++;
                    } else {
                        j--;
                    }
                }
            }
        }
    }
}