package demo;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class DequeDemo {
    public static void main(String[] args) {
        arrayDequeDemo();

        linkedListDemo();
    }

    private static void arrayDequeDemo() {
        Deque<String> strDeque = new ArrayDeque<>();
        strDeque.add("1");
        strDeque.offer("2");
        strDeque.addFirst("3");
        strDeque.addLast("4");
        strDeque.push("5");

        //pop方法移除队列第一个元素并返回该元素
        //若每个元素都是addFirst或push进来的，可实现类似栈的效果(LIFO)
        String pop = strDeque.pop();

    }

    private static void linkedListDemo() {
        Deque<String> strDeque = new LinkedList<>();
        //与ArrayDeque类似
    }
}
