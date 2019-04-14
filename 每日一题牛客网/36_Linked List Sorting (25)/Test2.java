package Day_36;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * @program: EveryDayTest
 * @Description: Linked List Sorting (25)
 * @Author: SOYANGA
 * @Create: 2019-04-14 22:00
 * @Version 1.0
 */
public class Test2 {
    /**
     * 抽象链表的Node结点为静态内部类
     */
    public static class Node implements Comparable {
        String address;
        int value;
        String next;

        public Node(String address, int value, String next) {
            this.address = address;
            this.value = value;
            this.next = next;
        }

        /**
         * 改变结点的指向的next结点
         *
         * @param next
         */
        public void changeNext(String next) {
            this.next = next;
        }

        /**
         * 覆写CompareTo方法 按value顺序排序
         *
         * @param o 比较结点
         * @return
         */
        @Override
        public int compareTo(Object o) {
            Node node = (Node) o;
            return this.value - node.value;
        }
    }

    public static void main(String[] args) {
        ArrayList<Node> nodeList = new ArrayList<>();
        Node[] nodes = new Node[100000];
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int count = in.nextInt();
            String head = in.next();
            //排除链表为空的情况
            if (head.equals("-1")) {
                System.out.println("0" + " " + "-1");
                return;
            }
            while (count-- > 0) {
                String address = in.next();
                int value = in.nextInt();
                String next = in.next();
                nodes[Integer.parseInt(address)] = new Node(address, value, next);
            }
            String p = head;
            //去除不在链表中的节点
            while (!p.equals("-1")) {
                Node node = nodes[Integer.parseInt(p)];
                nodeList.add(node);
                p = node.next;  //链表下一个位置
            }
            Collections.sort(nodeList);
            //改变排好序的链表的顺序
            for (int i = 0; i < nodeList.size() - 1; i++) {
                nodeList.get(i).changeNext(nodeList.get(i + 1).address);
            }
            //添加上最后的空指针 "-1"
            nodeList.get(nodeList.size() - 1).changeNext("-1");
            //输出的首行
            System.out.println(nodeList.size() + " " + nodeList.get(0).address);
            for (int i = 0; i < nodeList.size(); i++) {
                Node node = nodeList.get(i);
                System.out.print(node.address + " ");
                System.out.print(node.value + " ");
                System.out.println(node.next + " ");
            }
        }
    }
}
