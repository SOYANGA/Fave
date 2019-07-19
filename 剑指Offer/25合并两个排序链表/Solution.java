package Merge;

/**
 * @program: TSRTOffer
 * @Description: 25合并两个有序链表 迭代法
 * @Author: SOYANGA
 * @Create: 2019-07-19 23:06
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

        public ListNode Merge(ListNode list1, ListNode list2) {
            if (list1 == null) {
                return list2;
            }
            if (list2 == null) {
                return list1;
            }
            ListNode pHead = new ListNode(-1);
            ListNode head = pHead;
            while (list1 != null && list2 != null) {
                if (list1.val < list2.val) {
                    head.next = list1;
                    list1 = list1.next;
                } else {
                    head.next = list2;
                    list2 = list2.next;
                }
                head = head.next;
            }
            if (list1 != null) {
                head.next = list1;
            } else {
                head.next = list2;
            }
            return pHead.next;
        }
    }
}
