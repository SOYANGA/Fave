package isPalindrome;

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
    public boolean isPalindrome(ListNode head) {

        // if(head == null || head.next == null){
        //         return true;
        //     }
        ListNode last = null;
        for (ListNode temp = head; temp != null; temp = temp.next) {
            ListNode newNode = new ListNode(temp.val);
            newNode.next = last;
            last = newNode;
        }
        while (last != null) {
            if (last.val != head.val) {
                return false;
            }
            last = last.next;
            head = head.next;
        }
        return true;
    }
}