//try catch 
//public class Test2 {
//     public static void main(String[] args) {
//         try {
//             int a = 10;
//             int b = 0;
//             int c = a / b;
//             System.out.println(c);
//         } catch (ArrayIndexOutOfBoundsException e) {
//             System.out.println("Exception thrown  :" + e);
//         }
//     }
// }
//主方法中的方法
// public class Test2 {
//     public static void main(String[] args) {
//         add(1, 1);
//     }

//     public static void add(int i, int j) {
//         System.out.println(i * j);
//         return;
//     }
// }

//重载
// public class Test2 {
//     public static void main(String[] args) {
//         System.out.println(add(5, 5));
//         System.out.println(add(5, 5, 55));
//     }

//     public static int add(int x, int y) {
//         return x + y;
//     }

//     public static int add(int x, int y, int z) {
//         return x + y + z;
//     }
// }

//递归求20阶乘
import java.math.BigInteger; //大整数类   精度 还有一BigDecimal大浮点数类也是无限精度

public class TestFac {
    public static void main(String[] args) {
        Integer a = 20;
        BigInteger num = fac(new BigInteger(a.toString()));
        System.out.println(num);
    }

    public static BigInteger fac(BigInteger n) {
        BigInteger num = BigInteger.ZERO;
        if (n.compareTo(BigInteger.ONE) == 0) {
            num = BigInteger.ONE;
        } else {
            num = n.multiply(fac(n.subtract(BigInteger.ONE)));
        }
        return num;
    }

 }
// 示例：
// import java.math.BigInteger;

// public class Test2 {
// public static void main(String[] args) {
// Integer a = 60;
// BigInteger s = fac(new BigInteger(a.toString()));
// System.out.println(s);
// System.out.println(s.toString());
// System.out.println(s.toString().length());

// }

// public static BigInteger fac(BigInteger num) {
// BigInteger s = BigInteger.ZERO;
// if (num.compareTo(BigInteger.ONE) == 0) {
// s = BigInteger.ONE;
// } else {
// s = num.multiply(fac(num.subtract(BigInteger.ONE)));
// }
// return s;
// }
// }
// 求阶乘方法
// public class TestFac {
//     public static void main(String[] args) {
//         System.out.println(addArray(20));
//     }

//     public static long addArray(int num) {// 数组添加计算阶乘
//         long[] arr = new long[21];// 创建数组
//         arr[0] = 1;

//         int last = 0;
//         if (num >= arr.length) {
//             throw new IllegalArgumentException("传入的值太大");//抛出传入  的数太大异常
//         }
//         if (num < 0)
//             throw new IllegalArgumentException("必须为正整数!");//抛出不  合理参数异常
//         while (last < num) {// 建立满足小于传入数的while循环
//             arr[last + 1] = arr[last] * (last + 1);// 进行运算
//             last++;// last先进行运算，再将last的值加1
//         }
//         return arr[num];
//     }
// }

// public class TestFac{
// public static void main(String[]args){
// int n=20;
// System.out.println(Fac(n));
// }
// public static int Fac(int n){
// if(n==1){
// return 1;
// }else{
// return n*Fac(n-1);
// }

// }
// }