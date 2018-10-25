import java.util.Scanner;

abstract class Role {
    private String name;
    private int age;
    private String sex;
    private int group;

    public Role() {
        this(20);
    }

    public Role(int age) {
        this.setAge(age);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getGroup() {
        return this.group;
    }

    abstract void play();
}

class Employee extends Role {
    private int salary;
    public static int id;

    public Employee() {
        super();
    }

    public Employee(String sex) {
        super.setSex(sex);
        this.salary = 10000;
    }

    public void play() {
        System.out.println("succeed! 交出作业  (～￣ ▽ ￣)～  ▄︻┻┳══━一");
        System.out.println("--------------------------------------------");
        System.out.println("姓名：" + super.getName());
        System.out.println("年龄：" + super.getAge());
        System.out.println("性别：" + super.getSex());
        System.out.println("所属组：" + super.getGroup());
        System.out.println("加班费：" + this.salary);
        System.out.println("-------------------------------------------");
    }

    final void sing() {
        super.setGroup(1);
    }
}

class Manager extends Employee {
    final String vehicle;

    public Manager(String type) {
        super();
        this.vehicle = type;
    }

    public void print() {
        System.out.println("强行插入一则消息 （づ￣3￣）づ  ：");
        System.out.println("你领导开的车永远是:" + this.vehicle);
    }
}

public class Work3_5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入姓名罒ω罒：");
        String name = scanner.nextLine();

        System.out.println("请输入您的正确性别 ·(っ°Д°;)っ：");
        String sex = scanner.nextLine();
        Employee employee = new Employee(sex);
        employee.setName(name);
        employee.sing();
        employee.play();

        String type = "land rover";
        Manager manager = new Manager(type);
        manager.print();
    }
}