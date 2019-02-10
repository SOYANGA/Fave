package Merge_Two_Sorted_Lists;

/**
 * @program: LeetCode
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-02-10 22:26
 * @Version 1.0
 */

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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode newNode = new ListNode(0); //带头结点的新链表
        ListNode cur = newNode;
        while (l1 != null && l2 != null) {//谁小，新链表接谁
            if (l1.val > l2.val) {
                cur.next = l2;
                l2 = l2.next;
            } else {
                cur.next = l1;
                l1 = l1.next;
            }
            cur = cur.next;
        }
        if (l1 == null) {     //一个链表完了直接接另一个有序列表
            cur.next = l2;
        } else {
            cur.next = l1;
        }
        return newNode.next;
    }
}

