import static java.lang.Math.pow;

class PrintNarcNum {
    public void print() {
        int i = 0;
        for (i = 100; i < 1000; i++) {
            int count = 3; // 位数 为3
            int sum = 0; // 和
            int num = i; // 数
            while (num != 0) { // 取每一位进行运算
                sum += (int) pow(num % 10, count);
                num = num / 10;
            }
            if (sum == i) { // 判断 当每位数的三次方的和等于这个数本省也就是水仙花数
                System.out.println(i);
            }
        }
    }
}

public class Work3_4 {
    public static void main(String[] args) {
        PrintNarcNum num = new PrintNarcNum();
        num.print();
    }
}