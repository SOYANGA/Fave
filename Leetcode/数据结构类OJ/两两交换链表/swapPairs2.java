package SwapPairs;


/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */

/**
 * @program: LeetCode
 * @Description: 通俗易懂版两交换两个数组
 * @Author: SOYANGA
 * @Create: 2019-05-13 22:34
 * @Version 1.0
 */
// class ListNode {
//    int val;
//    ListNode next;
//
//    ListNode(int x) {
//        val = x;
//    }
//}

class Solution2 {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode newHead = head.next;
        while (pre.next != null && pre.next.next != null) {
            ListNode a = pre.next;
            ListNode b = a.next;
            pre.next = b;
            a.next = b.next;
            b.next = a;
            pre = a;
        }
        return newHead;
    }
}