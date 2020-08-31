package service.impl;

import service.SetUsageService;

import java.util.*;

/**
 * Created by jdd on 2017/8/21.
 */
public class SetUsageServiceImpl implements SetUsageService {
    @Override
    public void getDistinctObject() {
        Integer[] intArray = new Integer[]{8, 9, 8, 1, 1, 2, 2, 3, 4, 5, 6, 6};
        List<Integer> result = new ArrayList<>();
        //直接塞List
        for (Integer integer : intArray) {
            result.add(integer);
        }
        //或者addAll
        result.clear();
        result.addAll(Arrays.asList(intArray));
        System.out.println(result);

        result.clear();

        //通过Set中转(去除重复值)
        Set<Integer> tempSet = new TreeSet<>();
        for (Integer integer : intArray) {
            tempSet.add(integer);
        }
        //或者addAll
        tempSet.clear();
        tempSet.addAll(Arrays.asList(intArray));

        result.addAll(tempSet);
        System.out.println(result);
    }
}
