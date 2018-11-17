package GetIntersectionNode;

/**
 * Definition for singly-linked list.
 * public class ListNode2 {
 * int val;
 * ListNode2 next;
 * ListNode2(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
class ListNode2 {
    int val;
    ListNode2 next;

    ListNode2(int x) {
        val = x;
        next = null;
    }
}

class Solution2 {
    public ListNode2 getIntersectionNode(ListNode2 headA, ListNode2 headB) {
        if (headA == null || headB == null) {
            return null;
        }
        if (headA == headB) {
            return headA;
        }
        int num1 = 0;
        int num2 = 0;
        int count = 0;
        ListNode2 temp3 = headA;
        ListNode2 temp4 = headB;
        for (ListNode2 temp = headA; temp != null; temp = temp.next) {
            num1++;
        }
        for (ListNode2 temp = headB; temp != null; temp = temp.next) {
            num2++;
        }
        if (num1 > num2) {
            count = num1 - num2;
            while (count != 0) {
                temp3 = temp3.next;
                count--;
            }
        } else {
            count = num2 - num1;
            while (count != 0) {
                temp4 = temp4.next;
                count--;
            }
        }
        while ((temp3 != null) && (temp4 != null)) {
            if (temp3 == temp4) {
                return temp3;
            }
            temp3 = temp3.next;
            temp4 = temp4.next;
        }
        return null;

    }
}