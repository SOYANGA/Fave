package ReverseList;

/**
 * @program: TSRTOffer
 * @Description: 24反转链表 递归法
 * @Author: SOYANGA
 * @Create: 2019-07-19 23:02
 * @Version 1.0
 */
/*
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/
public class Solution2 {
    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }

        public ListNode ReverseList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode next = head.next;
            head.next = null;
            ListNode newHead = ReverseList(next);
            next.next = head;
            return newHead;
        }
    }
}
/*
递归法
 */