//////////////////
//1.1一段特殊的
abstract class A {
    public A() { // 3.调用父类构造方法
        this.print(); // 4.调用被子类覆写方法
    }

    public abstract void print();// .抽象方法
}

class B extends A {
    private int num = 100;

    public B(int num) { // 2.调用子类实例化对象
        super(); // 3.隐藏了一行语句，实际要先调用父类构造
        this.num = num;// 7.为类属性初始化
    }

    public void print() {// 此时子类对象的属性还没有初始化
        System.out.println(this.num);// 6.对应其数据类型的默认值（num 此时还是默认值 0）
    }
}

public class TestAbstractClass {
    public static void main(String[] args) {
        A a = new B(30);// 1子类实例化子类对象
        // 0
        a.print(); // 8.调用被子类覆写的抽方法的print //此时子类的对象属性已经初始化完毕 已经有赋值完为3.
        // 30
    }
}