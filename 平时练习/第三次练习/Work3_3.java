class A {
    public A() {
        System.out.println('A');
    }
}

class B extends A {
    public B() {
        System.out.println('B');
    }
}

class Work3_3{
    public static void main(String[] args) {
        B b = new B();
    }
}

/**
 * 1.没有主类 且B是子类A是父类 实例化子
对象之前是需要先调用父类构造方法产生父类对象，然后再调用子类构造方法的 
假如：没有错误的话 输出：
A
B

 */