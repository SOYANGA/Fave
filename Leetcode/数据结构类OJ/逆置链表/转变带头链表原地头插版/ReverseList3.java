package ReverseList;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */

class ListNode3 {
    int val;
    ListNode next;

    ListNode3(int x) {
        val = x;
    }
}

class Solution3 {
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pre = new ListNode(0);   ////变为带头链表   头节点
        pre.next = head;
        ListNode cur = pre.next; //头节点后的第一个节点
        int num = 0;           //链表长度
        while (cur != null) {
            cur = cur.next;
            ++num;
        }
        cur = pre.next;
        for (int i = 1; i < num; ++i) {  //头插次数
            ListNode t = cur.next;
            cur.next = t.next;
            t.next = pre.next;
            pre.next = t;    //头节点转变
        }
        return pre.next;
    }
}