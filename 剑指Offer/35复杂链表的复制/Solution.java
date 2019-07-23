package Clone;

/**
 * @program: TSRTOffer
 * @Description: 35复制链表的复制
 * @Author: SOYANGA
 * @Create: 2019-07-23 23:06
 * @Version 1.0
 */
/*
public class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}
*/
public class Solution {
    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null) {
            return null;
        }
        //1.链表进行赋值 1次遍历
        RandomListNode cur = pHead;
        while (cur != null) {
            RandomListNode clone = new RandomListNode(cur.label);//复制结点
            clone.next = cur.next; //对复制结点进行插入
            cur.next = clone;
            cur = clone.next;
        }

        //2.遍历链表对偶数结点的随机指向进行复制
        cur = pHead;
        while (cur != null) {
            RandomListNode clone = cur.next;
            if (cur.random != null) {
                clone.random = cur.random.next; //注意随机指针指向的也是复制链表中的结点
            }
            cur = clone.next;
        }
        //3.对链表进行拆分 拆分成两个链表（奇数偶数结点拆分）
        cur = pHead;
        RandomListNode pCloneHead = pHead.next;
        while (cur.next != null) {
            RandomListNode next = cur.next;//记录后继结点
            cur.next = next.next;//跨一个结点指向
            cur = next;
        }
        return pCloneHead;
    }

    public class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }
}