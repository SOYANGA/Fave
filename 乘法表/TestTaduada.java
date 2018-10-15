
// 1. 使用for循环打印乘法口诀表
import java.util.Scanner;

public class TestTaduada {
    public static void main(String[] args) {
        Tabuada mu1 = new Tabuada();
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt(); // 被System.in.read();坑了半天 
        mu1.setX(num);
        mu1.muTable();
        scanner.close();
    }
}

class Tabuada {
    private int x;

    public Tabuada() {
        System.out.println("请给乘法表输入一个值：例如9：99乘法表");
    }

    public void setX(int x) {
        this.x = x;

    }

    public int getX() {
        return this.x;
    }

    public void muTable() { // 输出乘法表方法
        for (int i = 1; i <= this.x; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(i + "*" + j + "=" + (i * j) + "\t");
                // System.out.printf("%d*%d=%d\t",i,j,i*j);
            }
            System.out.println();
        }
    }

}