package maxInWindows;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * @program: TSRTOffer
 * @Description: 滑动窗口最大值 利用大堆来实现，堆顶元素肯定时最大值，维护一个size大小的堆，就如同滑动窗口
 * @Author: SOYANGA
 * @Create: 2019-08-02 01:23
 * @Version 1.0
 */

public class Solution {
    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> ret = new ArrayList<>();
        if (size > num.length || size < 1) {
            return ret;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> o2 - o1);//大顶堆
        //先放入一组窗口初始化堆
        for (int i = 0; i < size; i++) {
            heap.add(num[i]);
        }
        ret.add(heap.peek());
        for (int i = 0, j = i + size; j < num.length; i++, j++) {
            heap.remove(num[i]);
            heap.add(num[j]);
            ret.add(heap.peek());
        }
        return ret;
    }
}
/*
滑动窗最大值，就可以变成一个大顶堆（大小为size），移除一个就元素，添加一个元素，并将堆顶放入结果ret中
*/
