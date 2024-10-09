package arthas;

import java.util.*;

/**
 * thread -i 3000 -n 5
 * @author cl
 */
public class test01 {
    public static void main(String[] args) {
        while (true) {
            System.out.println("按输入整数的循环次数或over结束：");
            String input = new Scanner(System.in).next();
            if ("over".equals(input)) {
                break;
            }
            int loop;
            try {
                loop = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                continue;
            }

            List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
            for (int i = 0; i < loop; i++) {
                listRandom(intList, i);
            }
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
