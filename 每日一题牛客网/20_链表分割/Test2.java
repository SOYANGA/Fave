package Day_20;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-25 22:41
 * @Version 1.0
 */


class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

public class Test2 {


    public ListNode partition(ListNode pHead, int x) {
        //创建两个新的链表节点
        ListNode leftNode = new ListNode(0);
        ListNode rightNode = new ListNode(0);
        ListNode head = leftNode;
        ListNode rhead = rightNode;
        //分解原链表
        while (pHead != null) {
            if (pHead.val < x) {
                leftNode.next = pHead;
                leftNode = leftNode.next;
            } else {
                rightNode.next = pHead;
                rightNode = rightNode.next;
            }
            pHead = pHead.next;
        }
        //合并新的两个链表
        leftNode.next = rhead.next;
        rightNode.next = null;
        return head.next;
    }
}
