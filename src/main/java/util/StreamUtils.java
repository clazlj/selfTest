package util;

import com.alibaba.fastjson.JSON;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by jdd on 2019/1/23.
 */
public class StreamUtils {
    public static void main(String[] args) {
        concatStream();

//        sequentialSum(5L);

        //groupList();

        summarizingInt();
    }

    private static void concatStream() {
        List<String> menList = new ArrayList<>();
        menList.add("男1");
        menList.add("男2");
        menList.add("男3");

        List<String> womenList = new ArrayList<>();
        womenList.add("女3");
        womenList.add("女2");
        womenList.add("女1");

        //合并流
        //创建一个懒惰连接的流，其元素是第一个流的所有元素，后跟第二个流的所有元素。
        Stream.concat(menList.stream(), womenList.stream()).skip(2).limit(3).forEach(System.out::println);
    }

    public static void summarizingInt() {
        IntSummaryStatistics collect = Stream.iterate(0, n -> n + 2).limit(10)
                .collect(Collectors.summarizingInt(i -> i));
        System.out.println(collect.getMax());
        System.out.println(collect.getCount());
        System.out.println(collect.getAverage());
    }


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
        //非并行的情况，reduce方法的combiner参数未起作用
        Long reduce0 = Stream.iterate(1L, i -> i + 1).limit(n)
                .reduce(0L, Long::sum, Long::max);

        Long reduce1 = Stream.iterate(1L, i -> i + 1).limit(n).parallel()
                .reduce(0L, Long::sum, Long::max);

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

    public static void groupList() {
        List<String> strList = new ArrayList<>(Arrays.asList("1", "2", "5", "2", "1", "2", "3", "1"));

        Map<String, List<String>> strListMap = strList.stream().collect(Collectors.groupingBy(i -> i));
        System.out.println(JSON.toJSONString(strListMap));

        Map<String, Long> strSizeMap = strList.stream()
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()));
        System.out.println(JSON.toJSONString(strSizeMap));
    }
}
