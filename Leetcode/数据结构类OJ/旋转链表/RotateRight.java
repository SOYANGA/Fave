package rotateRight;

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
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newhead = new ListNode(0);
        newhead.next = head;
        ListNode temp = head;
        ListNode cur = head;

        int count = 0;
        while (cur != null) {
            cur = cur.next;
            ++count;
        }
        k %= count;
        if (k == 0) {
            return head;
        }
        cur = head;
        for (int i = 0; i < k; i++) {
            cur = cur.next;
        }
        while (cur.next != null) {
            cur = cur.next;
            head = head.next;
        }
        temp = head.next;
        head.next = null;
        cur.next = newhead.next;
        newhead.next = temp;
        return newhead.next;
    }
}
