package service.impl;

import service.UseIteratorService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jdd on 2017/9/11.
 */
public class UseIteratorServiceImpl implements UseIteratorService {

    @Override
    public void iteratorMain() {
        List<Long> list1 = new ArrayList<>();
        list1.add(1L);
        list1.add(Long.valueOf(2));
        list1.add(Long.valueOf(3));
        list1.add(Long.valueOf(4));
        list1.add(Long.valueOf(5));

        List<Long> list2 = new ArrayList<>();
        list2.add(Long.valueOf(4));
        list2.add(Long.valueOf(5));
        list2.add(Long.valueOf(6));
        list2.add(Long.valueOf(7));
        list2.add(Long.valueOf(8));

        Iterator<Long> iterator = list1.iterator();
        while (iterator.hasNext()) {
            if (!list2.contains(iterator.next())) {
                iterator.remove();
            } else {
                iterator.remove();
            }
        }
    }
}
