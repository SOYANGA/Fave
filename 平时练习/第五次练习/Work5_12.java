public class Work5_12 {
    public static void main(String[] args) {
        Person person = new Person();
        person.setName("杨益行");
        person.setSalary(50);
        System.out.println(person);
        System.out.println(person.equals(person));
    }
}

class Person {
    private String name;
    private int age;
    private int salary;

    public Person() {
        this(20);
    }

    public Person(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String toString() {
        return "姓名:" + name +"\n"+
                "年龄:" + age +"\n"+
                "加薪:" + salary;

    }

    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!(object instanceof Person)) {
            return false;
        }
        Person per = (Person) object;
        return (per.salary == this.salary) && (per.name == this.name) && (per.age == per.age);
    }
}