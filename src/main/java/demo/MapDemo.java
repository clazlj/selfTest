package demo;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapDemo {
    public static void main(String[] args) {
        hashMapDemo();
        int tableSize = tableSizeFor(4);

        linkedHashMapDemo();

    }

    public static void hashMapDemo() {
        Map<String, String> map = new HashMap<>();

        int size = map.size();
//        ((HashMap) map).computeIfAbsent()


        // 键不能重复，值可以重复
        map.put("san", "张三");
        map.put("si", "李四");
        map.put("wu", "王五");
        map.put("wang", "老王");
        map.put("wang", "老王2");// 老王被覆盖
        map.put("lao", "老王");
        System.out.println("-------直接输出hashmap:-------");
        System.out.println(map);
        map.forEach((k,v)->{
            //hashmap遍历数组，每个数组又遍历链表

        });
    }

    private static int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        int MAXIMUM_CAPACITY = 1 << 30;

        return (n < 0) ? 1 : (n >= 1<<30) ? MAXIMUM_CAPACITY : n + 1;

    }

    private static void linkedHashMapDemo() {
        System.out.println("-------LinkedHashMap:-------");
        //默认accessOrder = false，即插入顺序FIFO，不受访问影响
        Map<Integer, String> insertionMap = new LinkedHashMap<>();
        for (int i = 0; i < 5; i++) {
            insertionMap.put(i, String.valueOf(i));
        }
        System.out.println("insertionMap轮询");
        insertionMap.forEach((k, v) -> System.out.println(k));
        insertionMap.get(3);
        System.out.println("insertionMap访问了元素3后轮询");
        insertionMap.forEach((k, v) -> System.out.println(k));

        //accessOrder = true，即访问顺序：访问了某个元素，则该元素会排列到集合的最后一位
        Map<Integer, String> accessMap = new LinkedHashMap<>(16, 0.75f, true);
        for (int i = 0; i < 5; i++) {
            accessMap.put(i, String.valueOf(i));
        }
        System.out.println("accessMap轮询");
        accessMap.forEach((k, v) -> System.out.println(k));
        accessMap.get(3);
        System.out.println("accessMap访问了元素3后轮询");
        accessMap.forEach((k, v) -> System.out.println(k));
    }
}
