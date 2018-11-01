package Chapter3.LotteryArray;

import java.util.Arrays;
import java.util.Scanner;

public class LotteryArray {
    public static void main(String[] args) {
        final int MAX = 10;
        Scanner in = new Scanner(System.in);
        //设置每行
        int[][] odds = new int[MAX][];
        for (int n = 0; n < MAX; n++) {
            odds[n] = new int[n + 1];
        }
        //给每行填入数据
        for (int i = 0; i < odds.length; i++) {   //row
            for (int j = 0; j < odds[i].length; j++) {  //odd
                /*
                 *compute binomial coefficient n*(n-1)*(n-2)*...(n-j+1)/(1*2*3*..*j)
                 */
                int lotterOdds = 1;
                for (int n = 1; n <= j; n++) {
                    lotterOdds = lotterOdds * (i - n + 1) / n;
                }
                odds[i][j] = lotterOdds;
            }
        }
        System.out.println("请输入你要打印的 ？：1二维数组：2/3：杨辉三角");
        int key = in.nextInt();
        if (key == 1) {
            //打印二维数组
            for (int[] row : odds) {
                for (int odd : row) {
                    System.out.printf("%4d", odd);
                }
                System.out.println();
            }
            System.out.println();
            System.out.println(Arrays.deepToString(odds));
        } else if (key == 2) {
            //杨辉三角
            new Printarr().printarr(odds);
        } else {
            int[][] arr = new int[MAX][MAX];
//            Arrays.fill(arr, 0);
            for (int i = 0; i < odds.length; i++) {
                for (int j = 0; j <= i; j++) {
                    if (j == 0) {
                        arr[i][j] = 1;
                    }
                    if (i == j) {
                        arr[i][j] = 1;
                    }
                    if (i > 1 && j > 0) {
                        arr[i][j] = arr[i - 1][j] + arr[i - 1][j - 1];
                    }
                }
            }
            //打印
            new Printarr().printarr(arr);
        }
    }
}

class Printarr {
    public void printarr(int arr[][]) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++)   //打印空格
            {
                System.out.printf("   ");
            }
            for (int j = 0; j <= i; j++)            //按行打印
            {
                System.out.printf("%-5d ", arr[i][j]);           //%-5d 这个就会让打印打印的好看 均衡
            }
            System.out.println();        //换行
        }

    }

}