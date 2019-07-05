package replaceSpace;

/**
 * @program: TSRTOffer
 * @Description: 5.替换空格 提醒特点
 * @Author: SOYANGA
 * @Create: 2019-07-14 01:12
 * @Version 1.0
 */
public class Solution {
    public String replaceSpace(StringBuffer str) {
        int oldIndex = str.length() - 1;
        //对字符串进行扩充 扩充到替换后的长度
        for(int i = 0; i <= oldIndex; i++) {
            if(str.charAt(i) == ' ') {
                str.append("  ");
            }
        }
        //从后向前进行替换
        int newIndex = str.length() - 1;
        while(oldIndex >= 0 && newIndex > oldIndex) {
            char c = str.charAt(oldIndex--);
            if(c == ' ') {
                str.setCharAt(newIndex--, '0');
                str.setCharAt(newIndex--, '2');
                str.setCharAt(newIndex--, '%');
            }else {
                str.setCharAt(newIndex--, c);
            }
        }
        return str.toString();
    }
}
