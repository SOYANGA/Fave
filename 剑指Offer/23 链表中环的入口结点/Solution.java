package EntryNodeOfLoop;

/**
 * @program: TSRTOffer
 * @Description: 23. 链表中环的入口结点
 * @Author: SOYANGA
 * @Create: 2019-07-19 22:41
 * @Version 1.0
 */
/*
 public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
*/
public class Solution {
    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }

        public ListNode EntryNodeOfLoop(ListNode pHead) {
            if (pHead == null || pHead.next == null) {
                return null;
            }
            //1.判读链表是否是环形链表
            ListNode fNode = pHead;
            ListNode sNode = pHead;
            while (fNode != null && fNode.next != null) {
                fNode = fNode.next.next;
                sNode = sNode.next;
                if (fNode == sNode) {
                    break;
                }
            }
            if (fNode == null || fNode != sNode) {
                return null;
            }
            //链表带环，且环位fNode,则求环的入口
            ListNode tNode = pHead;
            while (tNode != fNode) {
                tNode = tNode.next;
                fNode = fNode.next;
            }
            return tNode;
        }
    }
}
/*
这是一个数学问题求长度的问题
 */