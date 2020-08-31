package util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TestListUtils {
    public static void sortList() {
        List<Integer> list = Arrays.asList(1, 5, 6, 8, 10, 24);

        list.sort(Comparator.comparing((Integer i) -> i > 5));

        list.sort(Comparator.comparing((Integer i)->i.compareTo(1)));
    }
}
