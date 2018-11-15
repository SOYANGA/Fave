/**
 * 方法引用
 */
@FunctionalInterface
//函数式编程接口，只允许有一个方法
interface IUtil1<P, R> {  //泛型接口
    public R switchPara1(P p);
}

@FunctionalInterface
interface IUtil2<R> {
    public R switchPara2();
}

@FunctionalInterface
interface IUtil3<R, P> {
    public R compare(P p1, P p2);
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{ name=" + name + ", age=" + age + '}';
    }
}

@FunctionalInterface
//这是一个函数式编程接口，只允许有一个方法
interface IUtil4<R, PN, PA> {
    public R createPerson(PN p1, PA p2);
}

public class MethodQuoteTest {
    public static void main(String[] args) {
        System.out.println("1.引用静态方法：类名称：：static 方法名称");
        //相当于为方法起了一个别名
        IUtil1<Integer, String> iu = String::valueOf;//进行了方法引用
        String str = iu.switchPara1(1000);//相当于调用了Stringd.valueOf(1000)
        System.out.println(str.length());

        System.out.println("2.引用某个特定类的对象的方法：实例化对象名称：：普通方法");
        IUtil2<String> iu1 = "hello"::toUpperCase;//进行方法引用
        System.out.println(iu1.switchPara2());//转换的就是“hello”

        System.out.println("3.引用类中的普通方法:类名：：普通方法");
        IUtil3<Integer, String> iu3 = String::compareTo;
        System.out.println(iu3.compare("刘", "霍"));

        System.out.println("4.引用构造方法：类名：：new");
        IUtil4<Person, String, Integer> iu4 = Person::new;
        System.out.println(iu4.createPerson("SOYANGA", 25));//相当于调用Person类的构造方法
    }
}
/**
 * 引用之前都是针对引用类型，也就是只有数组、类、接口具备引用操作。
 * JDK1.8
 * 追加引用方法的概念
 * 实际上引用的本质就是别名。所以引用也是别名的使用。
 * 而方法引用的类型有一下四种类型
 * <p>
 * 1.引用静态方法：类名程：：static 方法名称；
 * 理解：调用了一个静态方法：且相当直接调用一个方法（参数要和其相同） 替换了方法
 * <p>
 * 2.引用某个特定的对象的方法： 实例化对象：：普通方法；
 * 理解：对象和方法全部替换（别名）-对象方法
 * <p>
 * 3.引用某个特定类的方法：类名称：：普通方法；
 * 理解：对象为一个类，方法为这个类的方法
 * <p>
 * 4.引用构造方法：类名称：：new。
 * 理解：替换了那个类构造方法
 */