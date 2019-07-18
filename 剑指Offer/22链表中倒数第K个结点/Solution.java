package findKthToTail;

/**
 * @program: TSRTOffer
 * @Description: 22链表中倒数第K个结点
 * @Author: SOYANGA
 * @Create: 2019-07-18 23:44
 * @Version 1.0
 */
public class Solution {

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }

        public ListNode FindKthToTail(ListNode head, int k) {
            if (head == null) {
                return null;
            }
            ListNode fNode = head;
            ListNode sNode = head;
            while (fNode != null && k-- > 0) {
                fNode = fNode.next;
            }
            if (k > 0) {
                return null;
            }
            while (fNode != null) {
                fNode = fNode.next;
                sNode = sNode.next;
            }
            return sNode;
        }
    }
}
/*
快慢指针法
 */