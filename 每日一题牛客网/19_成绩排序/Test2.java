package Day_19;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-25 00:17
 * @Version 1.0
 */

class Student implements Comparable {
    private String name;
    private int score;
    private int flag;

    public Student(String name, int score, int flag) {
        this.name = name;
        this.score = score;
        this.flag = flag;
    }


    @Override
    public String toString() {
        return name + " " + score;
    }

    @Override
    public int compareTo(Object obj) {
        Student st = (Student) obj;
        int temp = this.score - st.score;
        if (flag == 1) {
            return temp == 0 ? 1 : temp;//1保证先录排列在前
        }
        return temp == 0 ? 1 : -1 * temp;
    }
}

public class Test2 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            int flag = in.nextInt();
            in.nextLine();

            Set set = new TreeSet();
            for (int i = 0; i < n; i++) {
                String[] name_age = in.nextLine().split(" ");
                set.add(new Student(name_age[0], Integer.parseInt(name_age[1]), flag));

            }

            for (Iterator it = set.iterator(); it.hasNext(); ) {
                Student stu = (Student) it.next();
                System.out.println(stu.toString());
            }

        }
    }
}
