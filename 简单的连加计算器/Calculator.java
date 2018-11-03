import java.util.Scanner;

public class Work4_12 {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        UseCompute useCompute = new UseCompute();
        System.out.println("请输入操作数1");
        int result = in.nextInt();
        String operate = "+"; //不输出默认执行加操作
        while (operate != "exit") {
            System.out.println("请输入您想要的操作:退出请输入exit");
            operate = in.next();

            try {
                switch (operate) {
                    case "+": {
                        System.out.println("请输入操作数2");
                        int m = in.nextInt();
                        result = useCompute.useCom(new Add(), result, m);
                        break;
                    }
                    case "-": {
                        System.out.println("请输入操作数2");
                        int m = in.nextInt();
                        result = useCompute.useCom(new Subtract(), result, m);
                        break;
                    }
                    case "*": {
                        System.out.println("请输入操作数2");
                        int m = in.nextInt();
                        result = useCompute.useCom(new Mulitiply(), result, m);
                        break;
                    }
                    case "/": {
                        System.out.println("请输入操作数2");
                        int m = in.nextInt();
                        result = useCompute.useCom(new Divide(), result, m);
                        break;
                    }
                    case "=": {
                        useCompute.printResult();
                        break;
                    }
                    case "exit": {
                        operate = "exit";
                        break;
                    }
                    default: {
                        System.out.println("请输入操作数2");
                        int m = in.nextInt();
                        result = useCompute.useCom(new Add(), result, m);
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}

interface Compute {
    int computer(int n, int m) throws Exception;
}

class Add implements Compute {
    @Override
    public int computer(int n, int m) {
        return n + m;
    }
}

class Subtract implements Compute {
    @Override
    public int computer(int n, int m) {
        return n - m;
    }
}

class Mulitiply implements Compute {
    @Override
    public int computer(int n, int m) {
        return n * m;
    }
}

class Divide implements Compute {
    @Override
    public int computer(int n, int m) {
//        if (m != 0)
//            return n / m;
//        else
//            System.out.println("非法除零操作，请重新输入操作数二！");
//        return n;
//

        //错误 此处不是该异常产生的地方，应该返回给该方法调用者
        int result = 0;
        try {
            result = n / m;
        } catch (ArithmeticException e) {
            e.printStackTrace();
            result = n;
        } finally {
            return result;
        }
    }
}

class UseCompute {
    private int result = 0;

    public int useCom(Compute com, int one, int two) throws Exception {
        this.result = com.computer(one, two);
        return this.result;
    }

    public void printResult() {
        System.out.println("result=" + this.result);
    }
}

//1.输出结果：
//good  good
//2.如上

