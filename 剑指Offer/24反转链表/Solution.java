package ReverseList;

/**
 * @program: TSRTOffer
 * @Description: 24反转链表
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
public class Solution {
    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }

        public ListNode ReverseList(ListNode head) {
            ListNode newList = new ListNode(-1);
            while (head != null) {
                ListNode next = head.next;
                head.next = newList.next;
                newList.next = head;
                head = next;
            }
            return newList.next;
        }
    }
}
/*
不断头插夫
 */