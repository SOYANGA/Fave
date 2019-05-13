package reverseKGroup;


///**
// * Definition for singly-linked list.
// * public class ListNode {
// * int val;
// * ListNode next;
// * ListNode(int x) { val = x; }
// * }
// */
//class ListNode {
//    int val;
//    ListNode next;
//
//    ListNode(int x) {
//        val = x;
//    }
//}

/**
 * @program: LeetCode
 * @Description: 蓝图版解法
 * @Author: SOYANGA
 * @Create: 2019-05-13 22:34
 * @Version 1.0
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k < 2) { //特殊值 不用判断直接返回
            return head;
        }
        ListNode newhead = new ListNode(0);
        newhead.next = head;
        ListNode pre = newhead, cur = head;
        for (int i = 1; cur != null; i++) {
            if (i % k == 0) {
                pre = reverseOneKGroup(pre, cur.next);
                cur = pre.next;
            } else {
                cur = cur.next;
            }
        }
        return newhead.next;
    }

    public ListNode reverseOneKGroup(ListNode pre, ListNode next) {
        ListNode n1 = pre.next, n2 = n1.next;
        while (n2 != next) {
            n1.next = n2.next;
            n2.next = pre.next;
            pre.next = n2;
            n2 = n1.next;
        }
        return n1;
    }
}

