package printListFromTailToHead;

/**
 * @program: TSRTOffer
 * @Description: 6从尾到头打印链表 3种方法 递归 头插 栈实现非递归（反转链表）
 * @Author: SOYANGA
 * @Create: 2019-07-14 01:18
 * @Version 1.0
 * <p>
 * public class ListNode {
 * int val;
 * ListNode next = null;
 * <p>
 * ListNode(int val) {
 * this.val = val;
 * }
 * }
 */
/**
 *    public class ListNode {
 *        int val;
 *        ListNode next = null;
 *
 *        ListNode(int val) {
 *            this.val = val;
 *        }
 *    }
 *
 */

import java.util.ArrayList;
import java.util.Stack;

public class Solution {

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        return ListFromTailToHead3(listNode);
    }

    /**
     *1.递归方式进行逆序打印链表
     */
    public ArrayList<Integer> ListFromTailToHead1(ListNode listNode) {
        ArrayList<Integer> ret = new ArrayList<>();
        if (listNode != null) {
            ret.addAll(ListFromTailToHead1(listNode.next));
            ret.add(listNode.val);
        }
        return ret;
    }

    /**
     *2.链表头插法重构链表，再进行顺序遍历添加到队列中
     */
    public ArrayList<Integer> ListFromTailToHead2(ListNode listNode) {
        ListNode newNode = new ListNode(-1);
        while (listNode != null) {
            //保存后续节点，用于遍历
            ListNode oldNext = listNode.next;
            //头插
            listNode.next = newNode.next;
            newNode.next = listNode;
            listNode = oldNext;
        }
        ArrayList<Integer> ret = new ArrayList<>();
        newNode = newNode.next;
        while (newNode != null) {
            ret.add(newNode.val);
            newNode = newNode.next;
        }
        return ret;
    }

    /**
     *3.使用栈完美实现递归效果
     */
    public ArrayList<Integer> ListFromTailToHead3(ListNode listNode) {
        ArrayList<Integer> ret = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while (!stack.isEmpty()) {
            ret.add(stack.pop());
        }
        return ret;
    }
}
