package demo;

import java.util.HashMap;
import java.util.Map;
import java.util.function.IntSupplier;

public class ClosureDemo {
    int i;
    IntSupplier makeFun(int x) {
        int j = 0;
        return () -> x + i++;
    }

    public static void main(String[] args) {
        ClosureDemo c1 = new ClosureDemo();
        IntSupplier f1 = c1.makeFun(0);
        IntSupplier f2 = c1.makeFun(0);
        IntSupplier f3 = c1.makeFun(0);
        System.out.println(f1.getAsInt());
        System.out.println(f2.getAsInt());
        System.out.println(f3.getAsInt());

        Map<String, Double> m = new HashMap<>();
        m.put("pi", 3.14159);
        m.put("e", 2.718);
        m.put("phi", 1.618);
        m.entrySet().stream()
                .map(e -> e.getKey() + ": " + e.getValue())
                .forEach(System.out::println);
    }
}
