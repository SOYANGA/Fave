//1.创建一个Test类，包含有一个public权限的int型成员变量与一个char类型的成员变量，观察在main方法中的初始值。 
public class TestObject{
    public static void main(String[]args){
        Test test=new Test();
        System.out.println(test.i+"\n"+test.s);//char(默认值为\u0000)
                                               //int(默认值为0)
    }
}
class Test{
    public int i;
    public char s;
}
//2.编写一个程序，展示无论你创建了某个特定类的多少个对象，这个类的某个特定的static成员变量只有一个属性。

public class TestObject {
    public static void main(String[] args) {
        Country[] per = new Country[3];
        per[0] = new Country(18);
        per[1] = new Country(18);
        per[2] = new Country(20);
        for (int i = 0; i < per.length; i++) {
            per[i].getInfo();
        }
    }
}

class Country {
    public static String country = "中华人民共和国";
    public int age;

    public Country(int age) {
        this.age = age;
    }

    public void getInfo() {
        System.out.println("国家" + Country.country + "年龄" + this.age);
    }
}
//4.创建一个带默认构造方法（即无参构造）的类，在构造方法中打印一条消息"Hello Constructor";
//再为这个类添加一个重载构造方法，令其接收一个字符串参数，
//并在这个有参构造方法中把"Hello Constructor"和接收的参数一起打印出来。 
public class TestObject {
    public static void main(String[] args) {
        String s = "Hi How are you";
        Test test = new Test();

    }
}

class Test {

    public Test() {
        System.out.println("Hello Constructor");

    }

    public Test(String s) {
        this();
        System.out.println(s);
    }
}