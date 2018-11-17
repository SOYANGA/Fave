package GetIntersectionNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

class Solution2 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA==null||headB==null){
            return null;
        }
        if(headA==headB){
            return headA;
        }
        int num1=0;
        int num2=0;
        int count = 0;
        ListNode temp3 = headA;
        ListNode temp4 = headB;
        for(ListNode temp = headA;temp!=null;temp=temp.next){
            num1++;
        }
        for(ListNode temp = headB;temp!=null;temp=temp.next){
            num2++;
        }
        if(num1>num2){
            count = num1-num2;
            while(count!=0){
                temp3 = temp3.next;
                count--;
            }
        }else{
            count = num2-num1;
            while(count!=0){
                temp4=temp4.next;
                count--;
            }
        }
        while((temp3!=null)&&(temp4!=null)){
            if(temp3==temp4){
                return temp3;
            }
            temp3=temp3.next;
            temp4=temp4.next;
        }
        return null;

    }
}