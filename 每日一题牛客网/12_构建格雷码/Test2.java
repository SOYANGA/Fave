package Day_12;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-16 19:10
 * @Version 1.0
 */

/*
0
1


00
01
11
10

000
001
011
010
110
111
//递归
 
    //递归的思路是n位gray码是由n-1位gray码生成，举个例子简单一些：
 
    //比如求n=3的gray码，首先知道n=2的gray码是(00,01,11,10)
 
    //那么n=3的gray码其实就是对n=2的gray码首位添加0或1生成的，添加0后变成(000,001,011,010)
 
    //添加1后需要顺序反向就变成(110,111,101,100)
 
    //组合在一起就是(000,001,011,010,110,111,101,100) 
 */
public class Test2 {
    public static void main(String[] args) {
        for (String i : getGray(2)) {
            System.out.println(i);
        }
        System.out.println();
        for (String i : getGray(3)) {
            System.out.println(i);
        }

    }

    public static String[] getGray(int n) {
        String[] resultStrs = null;
        if (n == 1) {
            resultStrs = new String[]{"0", "1"};
            return resultStrs;
        } else {
            String[] strs = getGray(n - 1);
            resultStrs = new String[2 * strs.length];
            for (int i = 0; i < strs.length; i++) {
                resultStrs[i] = "0" + strs[i];
                resultStrs[resultStrs.length - i - 1] = "1" + strs[i];
            }
        }
        return resultStrs;
    }
}
