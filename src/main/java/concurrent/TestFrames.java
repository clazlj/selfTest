package concurrent;

public class TestFrames {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> method1(20));
        t1.setName("t1");
        t1.start();

        new Thread() {
            @Override
            public void run() {
                super.run();
            }
        };

        method1(10);
    }

    private static void method1(int x) {
        int y = x + 1;
        Object m = method2();
        System.out.println(m);
    }

    private static Object method2() {
        Object n = new Object();
        return n;
    }
}
