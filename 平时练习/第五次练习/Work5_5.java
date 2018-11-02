public class Work5_5 {
    public static void main(String[] args) throws Exception {
        int n = 4;
        int m = 25;
        if (n * m == 100) {
//            throw new MulException("错误乘法操作：两数相乘不允许等于100");
        }
        int i = 20;
        int j = 10;
        if (i / j == 2) {
            throw new DivException("错误除法操作：两数相除不允许等于2");
        }
    }
}

class MulException extends Exception {
    public MulException(String msg) {
        super(msg);
    }
}

class DivException extends Exception {
    public DivException(String msg) {
        super(msg);
    }
}
