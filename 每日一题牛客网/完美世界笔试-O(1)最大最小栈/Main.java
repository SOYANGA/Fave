package Test;

import java.util.Scanner;
import java.util.Stack;

/**
 * @program: EveryDayTest
 * @Description:
 * @Author: SOYANGA
 * @Create: 2019-04-15 19:30
 * @Version 1.0
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MinMaxStack minMaxStack = new MinMaxStack();
        while (scanner.hasNextInt()) {
            int count = scanner.nextInt();
            while (count-- > 0) {
                minMaxStack.push(scanner.nextInt());
            }
            minMaxStack.pop();
            System.out.print(minMaxStack.max() + "," + minMaxStack.min());
        }

    }

    static class MinMaxStack {
        public Stack<Integer> stack = new Stack<>();
        public Stack<Integer> minStack = new Stack<>();
        public Stack<Integer> maxStack = new Stack<>();

        public void push(int data) {
            stack.push(data);
            if (minStack.isEmpty() || maxStack.isEmpty()) {
                minStack.push(data);
                maxStack.push(data);
            } else {
                if (minStack.peek() >= data) {
                    minStack.push(data);
                } else if (minStack.peek() < data) {
                    minStack.push(minStack.peek());
                }
                if (maxStack.peek() <= data) {
                    maxStack.push(data);
                } else if (maxStack.peek() > data) {
                    maxStack.push(maxStack.peek());
                }
            }
        }

        public int pop() {
            maxStack.pop();
            minStack.pop();
            return stack.pop();
        }

        public int min() {
            return minStack.peek();
        }

        public int max() {
            return maxStack.peek();
        }

    }
}
