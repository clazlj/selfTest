package demo;

import java.util.Queue;

public class QueueDemo {
    public static <T> void printQueue(Queue<T> queue) {
        while (queue.peek() != null) {
            System.out.print(queue.remove() + " ");
        }
        System.out.println();
    }
}
