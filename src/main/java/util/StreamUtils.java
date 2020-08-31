package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by jdd on 2019/1/23.
 */
public class StreamUtils {

    public static void dealCollect() {
        Arrays.stream(new int[] { 1, 3, 5, 7, 15, 28, 37 }, 3, 6)
                .forEach(n -> System.out.format("%d ", n));

        int a = 1;
        //流生成List
        List<Integer> integerList = Stream.iterate(0, n -> n + 2).limit(20).collect(Collectors.toList());

        String integerStr = integerList.stream().map(String::valueOf).collect(Collectors.joining(","));

        List<Double> randomDouble = Stream.generate(Math::random).limit(5).collect(Collectors.toList());
    }

    public static long sequentialSum(long n) {
        return
                Stream.iterate(1L, i -> i + 1).limit(n).reduce(0L, Long::sum);
    }

    public static long parallelSum(long n) {
        return
                Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(0L, Long::sum);
    }

    public static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result:" + sum);
            if (duration < fastest) {
                fastest = duration;
            }
        }
        return fastest;
    }

    public static void mapAndFlatMap() {
        List<List<String>> strLists = new ArrayList<>();
        strLists.add(Arrays.asList("1", "5", "8"));
        strLists.add(new ArrayList<String>() {{
            add("3");
            add("9");
        }});

        //strLists.stream().map()

        List<String> joinList = strLists.stream().flatMap(i -> i.stream().map(String::valueOf)).collect(Collectors.toList());

        List abc = strLists.stream().map(i -> i.stream().map(String::valueOf)).collect(Collectors.toList());

    }
}