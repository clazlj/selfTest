package demo;

import java.util.concurrent.CompletableFuture;

public class FutureDemo {
    public static void main(String[] args) {
        CompletableFutureDemo();
    }

    private static void CompletableFutureDemo() {
        CompletableFuture<Void> task1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(4_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task1 执行");
            return null;
        });

        CompletableFuture<Void> task2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task2 执行");
            return null;
        });

        CompletableFuture<Void> task3 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task3 执行");
            return null;
        });

        CompletableFuture<Void> future = CompletableFuture.allOf(task1, task2, task3);

        //等待所有任务完成
        future.join();
    }
}
