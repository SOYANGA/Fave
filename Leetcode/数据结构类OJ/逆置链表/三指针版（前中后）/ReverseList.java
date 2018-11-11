package ReverseList;

/**
 * Definition for singly-linked list.
 * public class ReverseList.ListNode {
 * int val;
 * ReverseList.ListNode next;
 * ReverseList.ListNode(int x) { val = x; }
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
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        ListNode n1 = head;  //头
        ListNode n2 = n1.next; //中
        ListNode n3 = n2.next; //尾
        while (n2 != null) {
            n2.next = n1;
            n1 = n2;
            n2 = n3;
            if (n3 != null) {
                n3 = n3.next;
            }
        }
        head.next = null;
        return n1;
    }
}