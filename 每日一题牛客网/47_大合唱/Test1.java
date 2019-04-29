//package Day_47;
//
//import java.util.*;
//
///**
// * @program: EveryDayTest
// * @Description:
// * @Author: SOYANGA
// * @Create: 2019-04-28 18:08
// * @Version 1.0
// */
//public class Test1 {
//    static class Student implements Comparable {
//        public int id;
//        public int ablity;
//
//        public Student(int id, int ablity) {
//            this.id = id;
//            this.ablity = ablity;
//        }
//
//        @Override
//        public int compareTo(Object o) {
//            return this.ablity - ((Student) o).ablity;
//        }
//    }
//
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNextInt()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
//            int count = in.nextInt();
//            ArrayList studentList = new ArrayList();
//            for (int i = 0; i < count; i++) {
//                studentList.add(new Student(i, in.nextInt()));
//            }
//            //需要个数
//            int needCount = in.nextInt();
//            //相邻个学生的差值
//            int div = in.nextInt();
//            Collections.sort(studentList);
//            Iterator iterator = studentList.iterator();
//            int num = 1;
//            while (needCount-- > 0) {
//                while (iterator.hasNext()) {
//                    if()
//                }
//            }
//
//
//        }
//    }
//}
