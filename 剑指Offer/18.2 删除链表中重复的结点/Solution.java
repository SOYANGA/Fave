package deleteDuplication;

/**
 * @program: TSRTOffer
 * @Description: 18.2 删除链表中的重复的结点
 * @Author: SOYANGA
 * @Create: 2019-07-17 23:25
 * @Version 1.0
 */
/*
 public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
*/
public class Solution {
    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
    public ListNode deleteDuplication(ListNode pHead){
        if (pHead == null || pHead.next == null) { //只有0或者1个节点，直接返回该节点
            return pHead;
        }
        ListNode next = pHead.next;
        if (pHead.val == next.val) { //当前节点是重复结点
            while (next != null && pHead.val == next.val) {
                //跳过值与当前结点重复的所有结点（即跳过重复结点），直到第一个与当前结点不同的结点赋值给next
                next = next.next;
            }
            return deleteDuplication(next); //从next开始作为新的头节点开始进行删除重复结点
        } else {
            pHead.next = deleteDuplication(pHead.next); //并无重复结点，直接重下一个结点开始
            return pHead; //返回该结点
        }
    }
}
