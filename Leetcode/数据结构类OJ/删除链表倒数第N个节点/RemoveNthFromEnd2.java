package removeNthFromEnd.removeNthFromEnd;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
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
    public ListNode2 removeNthFromEnd(ListNode2 head, int n) {
        ListNode2 first=head;
        ListNode2 end=head;
        for(int i=0;i<n;i++) {
            end=end.next;
        }
        if(end==null){
            return first.next;
        }
        while(end.next!=null) {
            first=first.next;
            end=end.next;
        }
        first.next=first.next.next;
        return head;
    }
}