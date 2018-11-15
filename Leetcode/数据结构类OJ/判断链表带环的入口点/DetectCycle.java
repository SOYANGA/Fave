package detectCycle;

/**
 * Definition for singly-linked list.
 * class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                return EntryCycle(head, fast);
            }
        }
        return null;
    }

    public ListNode EntryCycle(ListNode head, ListNode meet) {
        ListNode cur = head;
        while (cur != meet) {
            cur = cur.next;
            meet = meet.next;
        }
        return cur;
    }
}