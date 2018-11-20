package addTwoNumbers;

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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode newhead = new ListNode(0);
        ListNode cur = newhead;
        int flag = 0;
        do {
            int count = ((l1 != null) ? (l1.val) : 0) + ((l2 != null) ? (l2.val) : 0) + flag;
            flag = (count >= 10) ? 1 : 0;   //表示进位
            count %= 10;                //每一位
            l1 = (l1 != null) ? (l1.next) : l1;   //是否迭代
            l2 = (l2 != null) ? (l2.next) : l2;   //是否迭代
            ListNode newnode = new ListNode(count);
            cur.next = newnode;
            cur = newnode;
        } while (l1 != null || l2 != null);
        if (flag == 1) {
            ListNode newnode = new ListNode(1);
            cur.next = newnode;
        }
        return newhead.next;
    }
}