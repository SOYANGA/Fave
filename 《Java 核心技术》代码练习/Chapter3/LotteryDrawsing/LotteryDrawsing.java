package Chapter3.LotteryDrawsing;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

import static javafx.scene.input.KeyCode.M;

public class LotteryDrawsing {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("How many numbers do you need draw:");
        int k = in.nextInt();

        System.out.print("What is the highest number you can draw:");
        int n = in.nextInt();

        //fill an array with numbers 1,2,3,4,5,6,...
        int[] numbers = new int[n];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i + 1;
        }

        //draw k numbers and put them into a secong array
        int[] result = new int[k];
        for (int i = 0; i < result.length; i++) {
            //随机数生成0 ~ n-1
            int r = (int) (Math.random() * n);
            //存入result中
            result[i] = numbers[r];

            //把最后一位数存放到总数的最后一位，并且n--。这个数就不会再被抽到
            numbers[r] = numbers[n - 1];
            n--;

        }
        //打印抽取的随机数
        Arrays.sort(result);//快排
        System.out.println("Bet the following Collection. It'll make you rich!");
        for (int temp : result) {  //打印
            System.out.print(temp + ",");
        }
        System.out.println("\n" + "Find:" + Arrays.binarySearch(result, 10));
    }
}

