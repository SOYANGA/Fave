package ReverseList;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
class ListNode2 {
    int val;
    ListNode2 next;

    ListNode2(int x) {
        val = x;
    }
}

class Solution2 {
    public ListNode2 reverseList(ListNode2 head) {
        ListNode2 last = null;
        for (ListNode2 temp = head; temp != null; temp = temp.next) {
            ListNode2 newNode = new ListNode2(temp.val);
            newNode.next = last;
            last = newNode;
        }
        return last;
    }
}
