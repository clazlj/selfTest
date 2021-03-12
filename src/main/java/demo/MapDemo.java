package demo;

import java.util.HashMap;
import java.util.Map;

public class MapDemo {
    public static void main(String[] args) {
        hashMapDemo();
        int tableSize = tableSizeFor(4);

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
}
