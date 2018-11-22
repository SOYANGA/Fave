package flatten;

/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {}

    public Node(int _val,Node _prev,Node _next,Node _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }
}
*/

class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {}

    public Node(int _val,Node _prev,Node _next,Node _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }
}

class Solution {
    public Node c(Node head) {
        if(head==null||(head.next==null&&head.child==null)){
            return head;
        }
        Node cur = head;
        while(cur!=null){
            Node next = cur.next;
            if(cur.child!=null){
                Node child = cur.child;
                cur.next = child;
                child.prev = cur;
                cur.child=null;
                while(child.next!=null&&cur.next!=null){
                    child=child.next;
                }
                child.next=next;
                if(next!=null){
                    next.prev = child;
                }
            }
            cur = cur.next;
        }
        return head;
    }
}