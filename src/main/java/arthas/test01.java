package arthas;

import java.util.*;

/**
 * @author cl
 */
public class test01 {
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        for (int i = 0; i < 30; i++) {
            listRandom(intList, i);
        }
    }

    private static List<Integer> listRandom(List<Integer> strList, int count) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> indexSet = new HashSet<>();
        // 生成随机下标
        while (indexSet.size() < count) {
            indexSet.add((int) (Math.random() * strList.size()));
        }
        // 根据下标添加元素
        for (Integer index : indexSet) {
            result.add(strList.get(index));
        }
        return result;
    }
}
