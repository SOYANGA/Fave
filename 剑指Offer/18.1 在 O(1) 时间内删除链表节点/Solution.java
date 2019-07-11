package deleteNode;

/**
 * @program: TSRTOffer
 * @Description: 18.1  在 O(1) 时间内删除链表节点
 * @Author: SOYANGA
 * @Create: 2019-07-17 23:18
 * @Version 1.0
 */
public class Solution {

    class ListNode {
        private int val;
        private ListNode next;
    }

    public ListNode deleteNode(ListNode head, ListNode tobeDelete) {
        if (head == null || tobeDelete == null) {
            return null;
        }
        if (tobeDelete.next != null) {
            //要删除的结点不是尾结点
            ListNode next = tobeDelete.next;
            tobeDelete.val = next.val;
            tobeDelete.next = next.next;
        } else {
            //是尾结点
            if (head == tobeDelete) {
                return null;
            } else {
                ListNode cur = head;
                while (cur.next != null) {
                    cur = cur.next;
                }
                cur.next = null;
            }
        }
        return head;
    }
}
