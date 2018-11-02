//3
public class Work5_34 {
    public static void main(String[] args) {
        Integer a = 55;
        Integer b = 55;
        System.out.println(a == b);//true
        //在-128 ~127之间用Integer直接赋值，Integer对象会在常量池中产生，
        //会复用已有对象；在这个区间外所有数据都会在对上产生，不回复用已有对象
        System.out.println(a == new Integer(55));//false
        //只有直接赋值才会将数据存放到常量池中
        Integer c = 129;
        Integer d = 129;
        System.out.println(c == d);//false
        //原因如上


        //4.单例模式
        //懒汉
        Singleton idler = Singleton.getInstance();
        idler.print();
        //饿汉
        Singleton2 hungry = Singleton2.getInstance();
        hungry.print();
    }
}

//4懒汉式-单例设计模式
class Singleton {
    private static Singleton idler;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (idler == null) {
            idler = new Singleton();
        }
        return idler;
    }

    public void print() {
        System.out.println("Idler,succeed!");
    }
}

//饿汉式-单例设计模式
class Singleton2 {
    private final static Singleton2 HUNGRY = new Singleton2();

    private Singleton2() {

    }

    public static Singleton2 getInstance() {
        return HUNGRY;
    }

    public void print() {
        System.out.println("The hungry Succeed!");
    }


}