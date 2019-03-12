package Day_09;


/*
有两个32位整数n和m，请编写算法将m的二进制数位插入到n的二进制的第j到第i位,其中二进制的位数从低位数到高位且以0开始。

给定两个数int n和int m，同时给定int j和int i，意义如题所述，请返回操作后的数，保证n的第j到第i位均为零，且m的二进制位数小于等于i-j+1。

测试样例：
1024，19，2，6

返回：1100

看清楚题
 */

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-12 19:13
 * @Version 1.0
 */


public class Test1 {
    public static void main(String[] args) {
        System.out.println(binInsert(1024, 19, 2, 6));
    }

    public static int binInsert(int n, int m, int j, int i) {
        return n | (m << j);
    }
}


//    public static int binInsert(int n, int m, int j, int i) {
//        Integer number1 = n;
//        Integer number2 = m;
//        byte numbyte1 = number1.byteValue();
//        byte numbyte2 = number2.byteValue();
//        byte[] numbitarray1 = getBitArray(numbyte1);
//        byte[] numbitarray2 = getBitArray(numbyte1);
//        String bitstr1 = byteToString(numbitarray1, numbitarray2, j, i);
//        return decodeBinaryString(bitstr1);
//
//    }
//
//    private static int decodeBinaryString(String bitstr1) {
//        int result;
//        if (bitstr1 == null) {
//            return 0;
//        }
//        int len = bitstr1.length();
//        if (len != 4 && len != 8) {
//            return 0;
//        }
//        if (len == 8) {
//            if (bitstr1.charAt(0) == '0') {
//                //正数
//                result = Integer.parseInt(bitstr1, 2);
//            } else {
//                //负数
//                result = Integer.parseInt(bitstr1, 2) - 256;
//            }
//        } else { //4bit处理
//            result = Integer.parseInt(bitstr1, 2);
//        }
//        return result;
//    }
//
//    private static String byteToString(byte[] numbitarray1, byte[] numbitarray2, int j, int i) {
//        for (int x = j; x <= i; x++) {
//            numbitarray1[x] = numbitarray2[x];
//        }
//        System.out.println(Arrays.toString(numbitarray1));
//        return Arrays.toString(numbitarray1);
//
//    }
//
//
//    /**
//     * 将byte转换为一个长度为8的byte数组，数组每个值代表bit
//     *
//     * @param numbyte
//     * @return
//     */
//    private static byte[] getBitArray(byte numbyte) {
//        byte[] bitArray = new byte[8];
//        for (int i = 7; i >= 0; i--) {
//            bitArray[i] = (byte) (numbyte & 1);
//            numbyte = (byte) (numbyte >> 1);
//        }
//        return bitArray;
//    }
