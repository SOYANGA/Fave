public class Work4_3 {
    public static void main(String[] args) {
        Cylinder cylinder = new Cylinder();
        cylinder.setColor("red");
        cylinder.setHeight(10);
        cylinder.setRadius(10);
        System.out.println(cylinder);
    }
}

interface A {
    final static double PI = 3.14;

    double area();
}

interface B {
    void setColor(String c);
}

interface C extends A, B {
    double area();

    void setColor(String c);

    double volume();
}

class Cylinder implements C {
    private double radius;
    private double height;
    private String color;

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public double volume() {
        return radius * radius * PI * height;
    }

    @Override
    public double area() {
        return radius * radius * PI * 2 + 2 * radius * PI * height;
    }

    public String toString() {
        return "圆柱体体积:" + (volume()) + "\n" +
                "圆柱表面积:" + (area()) + "\n" +
                "圆柱体颜色:" + (getColor());
    }


}

