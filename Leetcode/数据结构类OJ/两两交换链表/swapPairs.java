package SwapPairs;

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
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode n1 = head;
        ListNode n2 = n1.next;
        ListNode temp = n2;
        while (n1 != null & n2 != null) {
            ListNode n3 = n2.next;
            if (n3 != null && n3.next != null) {
                n1.next = n2.next.next;
                n2.next = n1;
                n1 = n3;
                n2 = n3.next;
            } else {
                n2.next = n1;
                n1.next = n3;
                break;
            }
        }
        return temp;
    }
}