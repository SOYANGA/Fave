package removeNthFromEnd.removeNthFromEnd;

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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode cur = new ListNode(0);
        // ListNode prev = null;
        cur.next = head;
        ListNode temp = cur;
        ListNode deletnode = cur;
        while ((n) != 0) {
            temp = temp.next;
            n--;
        }
        while (temp.next != null) {
            temp = temp.next;
            // if(temp==null){
            //     prev=deletnode;
            // }
            deletnode = deletnode.next;
        }
        deletnode.next = deletnode.next.next;
        return cur.next;
    }
}