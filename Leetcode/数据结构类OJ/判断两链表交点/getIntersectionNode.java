package GetIntersectionNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
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
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode p1 = headA, p2 = headB;
        while (p1 != p2) { // 两者走过路程相同，要么相交时相对，要么都走到尽头null相等

            p1 = (p1 != null) ? p1.next : headB;
            p2 = (p2 != null) ? p2.next : headA;

        }
        return p1;
    }
}