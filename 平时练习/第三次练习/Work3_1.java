class Father {
    private String name = "zhangjun";

    class Child {
        public void intoFather() {
            System.out.println(name);
        }
    }
}

public class Work3_1 {
    public static void main(String[] args) {
        Father.Child child = new Father().new Child();
        child.intoFather();
    }
}
