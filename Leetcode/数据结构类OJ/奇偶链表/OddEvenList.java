package oddEvenList;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        ListNode newhead = head; ///奇数头
        ListNode newhead2 = head.next; //偶数头
        ListNode cur = head.next;  //偶数位
        ListNode temp = head;     //奇数位
        while (temp.next != null && cur.next != null) {
            temp.next = cur.next;
            temp = temp.next;
            cur.next = temp.next;
            cur = cur.next;
        }
        temp.next = newhead2;
        return newhead;
    }
}