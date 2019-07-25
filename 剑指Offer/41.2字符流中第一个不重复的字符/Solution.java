package streamUnique;

import java.util.Queue;
import java.util.LinkedList;

/**
 * @program: TSRTOffer
 * @Description:42.1 字符流中第一个不重复的字符
 * @Author: SOYANGA
 * @Create: 2019-07-26 00:08
 * @Version 1.0
 */

public class Solution {
    //存放所有字符的哈希表
    private int[] cnts = new int[256];
    //存放结果的队列
    private Queue<Character> queue = new LinkedList();
    //Insert one char from stringstream
    public void Insert(char ch){
        cnts[ch]++;
        queue.add(ch);
        //只存放出现一次的数，且FIFO 第一个一定是第一次出现的
        while (!queue.isEmpty() && cnts[queue.peek()] > 1) {
            queue.poll();
        }
    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce(){
        return queue.isEmpty() ? '#' : queue.peek();
    }
}
