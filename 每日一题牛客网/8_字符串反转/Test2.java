package Day_08;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * 写出一个程序，接受一个字符串，然后输出该字符串反转后的字符串。例如：
 *
 * 输入描述:
 * 输入N个字符
 *
 *
 *
 * 输出描述:
 * 输出该字符串反转后的字符串
 *
 * 示例1
 * 输入
 * abcd
 * 输出
 * dcba
 */

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-11 19:15
 * @Version 1.0
 */
public class Test2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = new String();
        str = in.nextLine();
        StringBuilder str1 = new StringBuilder(str);
        System.out.println(str1.reverse());
    }
	//最优解
	    //public static void main(String[] args){        
		//Scanner sc=new Scanner(System.in);        
		//while(sc.hasNext()){            
		//String s=sc.nextLine();            
		//for(int i=s.length()-1;i>=0;i--){                
		//System.out.print(s.charAt(i));            
		//}        
		//}
}
