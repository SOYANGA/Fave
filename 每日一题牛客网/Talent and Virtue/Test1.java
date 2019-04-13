package Day_35;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @program: EveryDayTest
 * @Description: Talent and Virtue
 * @Author: SOYANGA
 * @Create: 2019-04-13 22:43
 * @Version 1.0
 */
public class Test1 {

    static class People implements Comparator {
        int id;
        int virtue;
        int talent;
        int grade;

        public People(int id, int virtue, int talent, int grade) {
            this.id = id;
            this.virtue = virtue;
            this.talent = talent;
            this.grade = grade;
        }


        @Override
        public int compare(Object o1, Object o2) {
            if (((People) o1).grade != ((People) o2).grade) {
                return ((People) o1).grade - ((People) o2).grade;
            } else if (((People) o1).virtue + ((People) o1).talent != ((People) o2).talent + ((People) o2).virtue) {
                return -((People) o1).virtue + ((People) o1).talent - ((People) o2).talent + ((People) o2).virtue;
            } else if (((People) o2).virtue != ((People) o1).virtue) {
                return -((People) o2).virtue - ((People) o1).virtue;
            } else {
                return ((People) o1).id - ((People) o2).id;
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            int count = in.nextInt();
            int lowNum = in.nextInt();
            int highNum = in.nextInt();
            ArrayList<People> list = new ArrayList();
            for (int i = 0; i < count; i++) {
                int id = in.nextInt();
                int virtue = in.nextInt();
                int talent = in.nextInt();
                if (virtue >= lowNum && talent >= lowNum) {
                    int grade = 0;
                    if (virtue >= highNum && talent >= highNum) {
                        grade = 1;
                    } else if (virtue >= highNum) {
                        grade = 2;
                    } else if (virtue >= talent) {
                        grade = 3;
                    } else {
                        grade = 4;
                    }
                    list.add(new People(id, virtue, talent, grade));
                }
            }
            list.sort(new Comparator<People>() {
                @Override
                public int compare(People o1, People o2) {
                    if (o1.grade != o2.grade) {
                        return o1.grade - o2.grade;
                    } else if ((o1.virtue + o1.talent) != (o2.talent + o2.virtue)) {
                        return -(((o1.virtue + o1.talent) - (o2.talent + o2.virtue)));
                    } else if (o1.virtue != o2.virtue) {
                        return -(o1.virtue - o2.virtue);
                    } else {
                        return o1.id - o2.id;
                    }
                }
            });
            System.out.println(list.size());
            for (People people : list) {
                System.out.println(people.id + " " + people.virtue + " " + people.talent);
            }
        }
    }
}


