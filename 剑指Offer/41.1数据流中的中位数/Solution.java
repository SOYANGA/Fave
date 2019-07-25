package streamIOfGetMedian;

import java.util.PriorityQueue;

/**
 * @program: TSRTOffer
 * @Description: 41.数据流中的中位数
 * @Author: SOYANGA
 * @Create: 2019-07-25 23:59
 * @Version 1.0
 */

public class Solution {
    /*大顶堆，存储左半边元素*/
    private PriorityQueue<Integer> left = new PriorityQueue<>((o1, o2) -> o2 - o1);
    /*小顶堆，存储右半边元素*/
    private PriorityQueue<Integer> right = new PriorityQueue<>();
    /*流中数据个数*/
    private int N = 0;

    public void Insert(Integer val) {
        //为了保证两个堆的平衡，定义当N为偶数时插入到右边，但是又因为，偶数时的数不一定总数为大数，所以就先插入到左堆中
        //从左堆顶抛出的元素插入到右堆中，维持平衡
        if ((N & 1) == 0) {
            left.add(val);
            right.add(left.poll());
        } else {
            right.add(val);
            left.add(right.poll());
        }
        N++;
    }

    public Double GetMedian() {
        if ((N & 1) == 0) {
            return (left.peek() + right.peek()) / 2.0;
        } else {
            return (double) right.peek();
        }
    }


}
/*
数据流中的中位数：大顶堆存储左半边的数，小顶堆存储右半边的数
右半边的数总是大于左半边的数

奇数时直接返回
*/
