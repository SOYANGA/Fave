class A {
    int y = 6;

    class Inner {
        static int y = 3;

        void show() {
            System.out.println(y);
        }
    }
}

class Demo {
    public static void main(String[] args) {
        A.Inner inner = new A().new Inner();
        inner.show();
    }
}
/**
 * a.成员内部类不能存在任何static 变量或方法,可以访问,但不能自己拥有 
 * 假如拥有:则编译报错 
 * Demo.java:5: 错误:
 * 内部类A.Inner中的静态声明非法 static int y = 3; 
 * ^ 修饰符 'static' 仅允许在常量变量声明中使用 
 * 1 个错误
 */