package Day_22;

import com.sun.org.apache.xpath.internal.operations.Plus;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-03-27 22:06
 * @Version 1.0
 */


class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

public class Test2 {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(0);
        ListNode t1 = l1;
        for (int i = 1; i <= 3; i++) {
            l1.next = new ListNode(i);
            l1 = l1.next;
        }
        ListNode l2 = new ListNode(0);
        ListNode t2 = l2;

        for (int i = 3; i >= 1; i--) {
            l2.next = new ListNode(i);
            l2 = l2.next;
        }

        ListNode node = plusAB(t1.next, t2.next);
        while (t1.next != null) {
            System.out.print(t1.next.val);
            t1 = t1.next;
        }
        System.out.println();
        while (t2.next != null) {
            System.out.print(t2.next.val);
            t2 = t2.next;
        }
        System.out.println();
        while (node != null) {
            System.out.print(node.val);
            node = node.next;
        }
    }

    public static ListNode plusAB(ListNode a, ListNode b) {
        ListNode newhead = new ListNode(0);
        ListNode temphead = newhead;
        int num = 0;
        while (a != null || b != null || num != 0) {
            int count = num;
            count += a == null ? 0 : a.val;
            count += b == null ? 0 : b.val;
            num = count / 10;
            newhead.next = new ListNode(count % 10);
            newhead = newhead.next;
            a = a == null ? null : a.next;
            b = b == null ? null : b.next;
        }
        return temphead.next;
//        while (a != null) {
//            int count = a.val + num;
//            if (count >= 10) {
//                count %= 10;
//                num = 1;
//            } else {
//                num = 0;
//            }
//            newhead.next = new ListNode(count);
//            newhead = newhead.next;
//            a = a.next;
//        }
//        while (b != null) {
//            int count = b.val + num;
//            if (count >= 10) {
//                count %= 10;
//                num = 1;
//            } else {
//                num = 0;
//            }
//            newhead.next = new ListNode(count);
//            newhead = newhead.next;
//            b = b.next;
//        }
//        return temphead.next;
    }
}
