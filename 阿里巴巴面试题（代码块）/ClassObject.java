 class HelloB {
    // 构造方法
    public HelloB() {
        System.out.println("1.父类构造方法");
    }

    // 非静态代码块
    {
        System.out.println("2.父类非静态代码块（构造块）");
    }
    // 静态代码块
    static {
        System.out.println("3.父类静态代码块");
    }
}

public class ClassObject extends HelloB {
    // 子类构造方法
    public ClassObject() {
        System.out.println("4.构造方法");
    }

    // 非静态代码块
    {
        System.out.println("5.非静态代码块（构造块）");
    }
    // 静态代码块
    static {
        System.out.println("6.静态代码块");
    }

    public static void main(String[] args) {
        System.out.println("7.----start---");
        new ClassObject();
        new ClassObject();
        System.out.println("8.----end-----");
    }
}

// 主类中定义的代码块的静态代码块优于主方法（main）执行 
// 1.主类中的静态代码块
// 假如有继承关系：（先实例化父类对象 再 实例化子类对象）前提实例化子类对象时
//         a.父类
//         b.子类
// 2.主方法（假如在主方法的第一句）
// 3.构造块（同上有继承关系且实例化子类对象时，先父类再子类）
// 4.构造方法（同上有继承关系且实例化子类对象时，先父类再子类）
// 5.最后再主方法接下来的语句
