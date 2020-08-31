package util;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapHandler {

    public static void mapNewCharacter() {
        Map<String, Integer> workerMap = new LinkedHashMap();
        workerMap.put("abc", 1);
        workerMap.put("def", 2);
        workerMap.put("ghi", 3);
        workerMap.put("jkl", 4);

        //workerMap.put("xyz", null);

        //取xyz的值，没有返回0
        //老方式
        int result = 0;
        if (workerMap.containsKey("xyz")) {
            result = workerMap.get("xyz");
        }
        //新方式（接口提供的默认方法）
        //注意，这一方法仅在没有映射时才生效。比如，如果键被显式地映射到了空值，那么该方法是不会返回设定的默认值。
        result = workerMap.getOrDefault("xyz", 0);
    }
}
