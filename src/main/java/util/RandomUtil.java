package util;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtil {
    /**
     * 共享该实例是线程安全的
     * 但会因竞争同一seed 导致的性能下降
     */
    static Random random = new Random();

    public static void main(String[] args) {
        randomGenerateInt();
        threadLocalRandomGenerateInt();
    }

    private static void randomGenerateInt() {
        System.out.println("Random生成");
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(10));
        }
    }

    /**
     * JDK7之后，可以使用ThreadLocalRandom来获取随机数
     */
    private static void threadLocalRandomGenerateInt() {
        System.out.println("ThreadLocalRandom生成");
        ThreadLocalRandom localRandom = ThreadLocalRandom.current();
        for (int i = 0; i < 10; i++) {
            System.out.println(localRandom.nextInt(10));
        }
    }
}
