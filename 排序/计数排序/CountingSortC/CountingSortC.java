package CountingSortC;

/**
 * @program: Sort
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-02-13 17:46
 * @Version 1.0
 */


class Person {
    public String name;
    public Integer age;
    public Integer gade;

    public Person(String name, Integer age, Integer gade) {
        this.name = name;
        this.age = age;
        this.gade = gade;
    }

}

public class CountingSortC {
    public static void main(String[] args) {
//        CountingSortC.Person person1 = new CountingSortC.Person("1",1,1);
//        CountingSortC.Person person2 = new CountingSortC.Person("2",2,2);
//        CountingSortC.Person person3 = new CountingSortC.Person("3",3,3);
//        CountingSortC.Person person4 = new CountingSortC.Person("4",1,4);
//
//        CountingSortC.Person[] testArray = new CountingSortC.Person[]{person1,person2,person3,person4};

        int[] num = new int[]{0, 8, 5, 9, 2, 7, 6, 3, 1, 4};
        countingsortC(num, 10);
        for (int temp : num) {
            System.out.print(temp + ",");
        }
    }

    public static void countingsortC(int[] a, int n) {
        int max = a[n - 1];
        int min = a[0];
        for (int i = 0; i < n; i++) {
            if (a[i] > max) {
                max = a[i];
            }
            if (a[i] < min) {
                min = a[i];
            }
        }
        int range = max - min + 1;    //找出最大最小数确定数据范围
        int[] tmp = new int[range];//创建一个与范围的小相同的临时数组
        for (int i = 0; i < n; i++) {
            tmp[i] = 0;
        }
        //计算每个数出现次数(在这个范围内)
        for (int i = 0; i < n; i++) {
            tmp[a[i] - min]++;
        }

        int index = 0;//目标数组的下标。
        for (int i = 0; i < range; i++) {
            while (((tmp[i])--) != 0) { //排序数组中不为0的下标加上最小值存储到临时数组中。
                a[index++] = i + min;
            }
        }
    }
}
