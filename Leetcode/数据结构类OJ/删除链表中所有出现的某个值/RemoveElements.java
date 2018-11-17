package removeElements;

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
    public ListNode removeElements(ListNode head, int val) {
        ListNode newhead = new ListNode(0);
        newhead.next = head;
        ListNode newnode = newhead;
        for (ListNode temp = head; temp != null; temp = temp.next) {
            if (temp.val != val) {
                newnode = newnode.next;
                newnode.val = temp.val;
            }
        }
        newnode.next = null;
        return newhead.next;
    }
}