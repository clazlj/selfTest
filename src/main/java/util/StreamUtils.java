package util;

import com.alibaba.fastjson.JSON;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by jdd on 2019/1/23.
 */
public class StreamUtils {
    public static void main(String[] args) {
//        concatStream();

//        sequentialSum(5L);

        //groupList();

//        mapAndFlatMap();

//        summarizingInt();

//        parallelStream();

//        generateNumPair();

//        printFibonacci();

        compareSequentialAndParallel(10_000_000L);
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

    public static void parallelStream() {
        // 核数
        int count = Runtime.getRuntime().availableProcessors();
        System.out.println(count);

        List<Integer> result = Arrays.asList(1, 2, 3, 4, 6, 7, 8, 9, 10);

        List<Integer> result1 = result.stream().map(i -> i + 1).collect(Collectors.toList());

        List<Integer> result2 = result.parallelStream().map(i -> i + 1).collect(Collectors.toList());
    }

    /**
     * 使用flatMap
     * 生成数对： 给定两个数字列表，返回所有的数对。
     * 例如，给定列表[1, 2, 3]和列表[3, 4]，应该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]。
     * 为简单起见，你可以用有两个元素的数组来代表数对。
     */
    public static void generateNumPair() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);

        // 并且限制两个数的和是3的倍数
        List<Integer[]> result = numbers1.stream()
                .flatMap(i -> numbers2.stream().filter(j -> (i + j) % 3 == 0).map(j -> new Integer[]{i, j}))
                .collect(Collectors.toList());

        result.forEach(i -> System.out.println(Arrays.toString(i)));
    }

    public static void printFibonacci() {
        // 斐波纳契数列，数列中开始的两个数字是0和1，后续的每个数字都是前两个数字之和。如0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55 …
        // 斐波纳契元组序列与此类似，是数列中数字和其后续数字组成的元组构成的序列：(0, 1),(1, 1), (1, 2), (2, 3), (3, 5), (5, 8), (8, 13), (13, 21) …
        // 利用Stream.iterate生成无限流，limit截取前20个
        // 最终取每个数组的第1个元素
        int[] initArr = new int[]{0, 1};
        Stream.iterate(initArr, t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .map(t -> t[0])
                .forEach(System.out::println);
    }

    /**
     * 前n个自然数求和，比较顺序流和并行流
     * 现象：Stream.iterate并行流反而更慢。
     * 原因：Stream.iterate在本质上是顺序的，本身不易并行化。具体来说，iterate很难分割成能够独立执行的小块，因为每次应用这个函数都要依赖前一次应用的结果
     */
    public static void compareSequentialAndParallel(long limit) {
        System.out.println("处理器数量：" + Runtime.getRuntime().availableProcessors());

        System.out.println("求和Stream.iterate顺序执行耗时：" + getSumTime(i ->
                Stream.iterate(1L, j -> j + 1)
                        .limit(i)
                        .reduce(0L, Long::sum), limit));

        System.out.println("求和Stream.iterate并行执行耗时：" + getSumTime(i ->
                Stream.iterate(1L, j -> j + 1)
                        .limit(i)
                        .parallel()
                        .reduce(0L, Long::sum), limit));

        System.out.println("---------------------------");

        List<Long> numList = Stream.iterate(1L, j -> j + 1)
                .limit(limit).collect(Collectors.toList());

        System.out.println("求和List顺序执行耗时：" + getSumTimeWithList(list -> list.stream().reduce(0L, Long::sum), numList));

        System.out.println("求和List并行执行耗时：" + getSumTimeWithList(list -> list.stream().parallel().reduce(0L, Long::sum), numList));

    }

    private static long getSumTime(Consumer<Long> consumer, long limit) {
        long total = 0;
        int loopCount = 10;
        for (int count = 0; count < loopCount; count++) {
            long start = System.currentTimeMillis();

            consumer.accept(limit);

            total += System.currentTimeMillis() - start;
        }

        return total / loopCount;
    }

    private static long getSumTimeWithList(Consumer<List<Long>> consumer, List<Long> numList) {
        long total = 0;
        int loopCount = 10;
        for (int count = 0; count < loopCount; count++) {
            long start = System.currentTimeMillis();

            consumer.accept(numList);

            total += System.currentTimeMillis() - start;
        }

        return total / loopCount;
    }
}
