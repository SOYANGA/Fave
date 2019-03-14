package Day_11;

import java.util.*;


/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-14 20:40
 * @Version 1.0
 */
public class Test1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] nums = new int[10];
        String numStr = in.nextLine();
        for (int i = 0; i < numStr.length(); i++) {
            nums[numStr.charAt(i) - '0']++;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                System.out.println(i + ":" + nums[i]);
            }
        }
    }

}


//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        String numStr = in.nextLine();
//        char[] chars = numStr.toCharArray();
//        List<Integer> list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            list.add(0);//初始化每个item都是0
//        }
//        for (int i = 0; i < chars.length; i++) {
//            list.set(chars[i] - '0', list.get(chars[i] - '0') + 1);
//        }
//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i) > 0) {
//                System.out.println(i + ":" + list.get(i));
//            }
//        }
//    }


//    public static void main(String[] args) {
//        HashMap<Character, Integer> map = new HashMap<>();
//
//        Scanner in = new Scanner(System.in);
//        while (in.hasNextInt()) {//注意while处理多个case
//            int num = in.nextInt();
//            char[] numStr = Integer.toString(num).toCharArray();
//            for (char c : numStr) {
//                if (map.containsKey(c)) {
//                    map.put(c, map.get(c) + 1);
//                } else {
//                    map.put(c, 1);
//                }
//            }
//            //1.将Map集合转变为Set集合
//            Set<Map.Entry<Character, Integer>> set = map.entrySet();
//            //2.获取iterator对象
//            Iterator<Map.Entry<Character, Integer>> iterator = set.iterator();
//            //3.输出
//            while (iterator.hasNext()) {
//                //取出每个Map.Entry对象
//                Map.Entry<Character, Integer> entry = iterator.next();
//                if (entry.getValue() > 0) {
//                    System.out.println(entry.getKey() + ":" + entry.getValue());
//                }
//            }
//        }
//    }
