package reverseKGroup;

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
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k < 2) { //特殊值 不用判断直接返回
            return head;
        }
        ListNode dummy = new ListNode(0);  //改为带头节点的链表  作为返回值的头节点（其实就是第一组翻转后的头节点）
        ListNode pre = dummy;  //作为头节点（移动的）
        dummy.next = head;
        ListNode cur = pre.next;   //一个组的第一个值（头节点的下一个值）  （头插对象）
        int num = 0;           //记录链表长度，作为分组翻转依据
        while (cur != null) {
            cur = cur.next;
            ++num;
        }
        while (num >= k) {          //判断是否翻转
            cur = pre.next;        //一个组的第一个节点
            for (int i = 1; i < k; ++i) {   //头插计数   头插次数为k-1
                ListNode t = cur.next;      //基于有头节点的头插
                cur.next = t.next;
                t.next = pre.next;
                pre.next = t;
            }
            pre = cur;              //转变头位置 为上一组的尾节点
            num -= k;               //未翻转链表长度
        }
        return dummy.next;   //返回第一组的第一个数据   （转变为无头链表）
    }
}

